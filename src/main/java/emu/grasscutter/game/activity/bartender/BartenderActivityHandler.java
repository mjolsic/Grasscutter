package emu.grasscutter.game.activity.bartender;

import emu.grasscutter.Grasscutter;
import emu.grasscutter.data.GameData;
import emu.grasscutter.data.binout.MainQuestData;
import emu.grasscutter.data.common.ItemParamData;
import emu.grasscutter.data.excels.BartenderFormulaData;
import emu.grasscutter.data.excels.BartenderOrderData;
import emu.grasscutter.data.excels.BartenderTaskData;
import emu.grasscutter.game.activity.ActivityHandler;
import emu.grasscutter.game.activity.GameActivity;
import emu.grasscutter.game.activity.PlayerActivityData;
import emu.grasscutter.game.player.Player;
import emu.grasscutter.game.props.ActivityType;
import emu.grasscutter.game.props.WatcherTriggerType;
import emu.grasscutter.net.proto.ActivityInfoOuterClass;
import emu.grasscutter.net.proto.ItemParamOuterClass.ItemParam;
import emu.grasscutter.net.proto.BartenderGetFormulaRspOuterClass.BartenderGetFormulaRsp;
import emu.grasscutter.net.proto.BartenderCompleteOrderReqOuterClass.BartenderCompleteOrderReq;
import emu.grasscutter.net.proto.BartenderCompleteOrderRspOuterClass.BartenderCompleteOrderRsp;
import emu.grasscutter.net.proto.RetcodeOuterClass.Retcode;
import emu.grasscutter.server.packet.send.PacketActivityInfoNotify;

import emu.grasscutter.utils.JsonUtils;
import emu.grasscutter.utils.Utils;

import static emu.grasscutter.game.quest.enums.QuestCond.QUEST_COND_ACTIVITY_COND;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@GameActivity(ActivityType.NEW_ACTIVITY_BARTENDER)
public class BartenderActivityHandler extends ActivityHandler {

    @Override
    public void onInitPlayerActivityData(PlayerActivityData playerActivityData) {
        BartenderPlayerData bartenderPlayerData = BartenderPlayerData.create();

        // hardcoding to add watcher progress for unlocking starter formula
        playerActivityData.getWatcherInfoMap().get(15062002).setCurProgress(
            bartenderPlayerData.getUnlockFormulaList().size()
        );
        
        playerActivityData.setDetail(bartenderPlayerData);
    }

    @Override
    public void onProtoBuild(PlayerActivityData playerActivityData, ActivityInfoOuterClass.ActivityInfo.Builder activityInfo) {
        BartenderPlayerData bartenderPlayerData = getBartenderPlayerData(playerActivityData);
        activityInfo.setBartenderInfo(bartenderPlayerData.toProto());
    }

    public void addActivityQuest(Player player) {
        // v2.5 bartender activity quests conditions
        // Set<Integer> activityQuestCond = Set.of(5062001, 5062002, 5062003, 5062004, 5062005, 5062006, 5062007, 5062008, 5062009);
        
        // useable when hidden refractoring is done
        // activityQuestCond.forEach(c -> player.getQuestManager().queueEvent(QUEST_COND_ACTIVITY_COND, c, 1));
        // hard coding to add starter quest, not in use
        if (player.getQuestManager().getQuestById(7050201) == null 
            && player.getActivityManager().meetsCondition(5062001)) {
            player.getQuestManager().addQuest(7050201);
        }
    }

    public BartenderPlayerData getBartenderPlayerData(PlayerActivityData playerActivityData) {
        if (playerActivityData.getDetail() == null || playerActivityData.getDetail().isBlank()) {
            onInitPlayerActivityData(playerActivityData);
            playerActivityData.save();
        }

        return JsonUtils.decode(playerActivityData.getDetail(), BartenderPlayerData.class);
    }

    public void unlockTask(PlayerActivityData playerActivityData, BartenderPlayerData bartenderPlayerData) {
        Player player = Grasscutter.getGameServer().getPlayerByUid(playerActivityData.getUid());
        if (player == null) return;

        LocalDate currentDate = LocalDate.ofInstant(Instant.ofEpochSecond(Utils.getCurrentSeconds()), ZoneId.systemDefault());
        LocalDate activityBeginDate = LocalDate.ofInstant(getActivityConfigItem().getBeginTime().toInstant(), ZoneId.systemDefault());;
        int daysBetween = (int) ChronoUnit.DAYS.between(activityBeginDate, currentDate) + 1;

        int[] added = new int[]{0};
        GameData.getBartenderTaskDataMap().values().stream()
            .filter(taskData -> daysBetween >= taskData.getUnlockDayCount())
            .filter(taskData -> player.getActivityManager().meetsCondition(taskData.getMeetConditionId())) // activity condition met
            .filter(taskData -> !bartenderPlayerData.getUnlockTask().containsKey(taskData.getId())) // get task that player dont have
            .forEach(taskData -> {
                // accept task's quest
                MainQuestData mainQuestData = GameData.getMainQuestDataMap().get(taskData.getParentQuestId());
                if (mainQuestData == null) return;

                int firstOrderSubQuestId = Stream.of(mainQuestData.getSubQuests())
                    .filter(subQuestData -> subQuestData.getOrder() == 1)
                    .map(subQuestData -> subQuestData.getSubId()).findFirst().orElse(0);

                if (firstOrderSubQuestId <= 0) return;
                player.getQuestManager().addQuest(firstOrderSubQuestId);

                // add bartender task item
                bartenderPlayerData.getUnlockTask().put(
                    taskData.getId(),
                    BartenderPlayerData.TaskInfoItem.create(taskData.getId())
                );
                added[0] += 1;
            });

        if (added[0] > 0) {
            playerActivityData.setDetail(bartenderPlayerData);
            playerActivityData.save();
            player.sendPacket(new PacketActivityInfoNotify(toProto(
                playerActivityData,
                player.getActivityManager().getConditionExecutor()
            )));
        }
    }

    public void finishTask(Player player, PlayerActivityData playerActivityData, BartenderPlayerData bartenderPlayerData, int questId) {
        bartenderPlayerData.getUnlockTask().values().stream()
            .filter(taskItem -> !taskItem.isFinish())
            .filter(taskItem -> GameData.getBartenderTaskDataMap().get(taskItem.getId()).getParentQuestId() == (int) questId / 100)
            .forEach(taskItem -> {
                taskItem.setFinish(true);
                player.getActivityManager().triggerWatcher(
                    WatcherTriggerType.TRIGGER_BARTENDER_FINISH_STORY_MODULE, 
                    String.valueOf(taskItem.getId())
                );
            });
    }

    public BartenderGetFormulaRsp getFormula(Player player, int questId, List<ItemParam> itemList) {
        Optional<PlayerActivityData> playerData = player.getActivityManager()
            .getPlayerActivityDataByActivityType(ActivityType.NEW_ACTIVITY_BARTENDER);

        BartenderFormulaData bartenderFormulaData = GameData.getBartenderFormulaDataMap().values().stream()
            .filter(x -> x.matchFormula(itemList.stream().map(item -> new ItemParamData(item.getItemId(), item.getCount())).toList()))
            .findFirst().orElse(null);
        
        BartenderGetFormulaRsp.Builder proto = BartenderGetFormulaRsp.newBuilder();
        if (!playerData.isPresent() || bartenderFormulaData == null) return proto.setRetcode(Retcode.RET_FAIL_VALUE).build();
        // might be missing task, currently questId unused

        proto.setFormulaId(bartenderFormulaData.getId())
            .addAllAffixList(bartenderFormulaData.getAffixes())
            .setIsNew(!getBartenderPlayerData(playerData.get()).getUnlockFormulaList().contains(bartenderFormulaData.getId()));

        return proto.build();
    }

    public BartenderCompleteOrderRsp completeOrder(Player player, BartenderCompleteOrderReq req) {
        Optional<PlayerActivityData> playerData = player.getActivityManager()
            .getPlayerActivityDataByActivityType(ActivityType.NEW_ACTIVITY_BARTENDER);

        BartenderFormulaData bartenderFormulaData = GameData.getBartenderFormulaDataMap().values().stream()
            .filter(x -> x.matchFormula(req.getItemListList().stream().map(item -> new ItemParamData(item.getItemId(), item.getCount())).toList()))
            .findFirst().orElse(null);
        
        BartenderCompleteOrderRsp.Builder proto = BartenderCompleteOrderRsp.newBuilder();
        if (!playerData.isPresent() || bartenderFormulaData == null) return proto.setRetcode(Retcode.RET_FAIL_VALUE).build();

        // assumming multiple orders in queue
        BartenderOrderData bartenderOrderData = req.getOptionalOrderListList().stream()
            .map(orderId -> GameData.getBartenderOrderDataMap().get(orderId))
            .filter(orderData -> orderData != null)
            .filter(orderData -> orderData.getFormulaId() == bartenderFormulaData.getId()) // find order with the right formula
            .filter(orderData -> orderData.getBartenderCupSize() == BartenderCupSize.BARTENDER_CUP_NONE
                || orderData.getBartenderCupSize() == BartenderCupSize.getTypeByValue(req.getCupSize())) // check if order's cupsize is right
            .findFirst().orElse(null);

        if (bartenderOrderData == null) return proto.setRetcode(Retcode.RET_FAIL_VALUE).build();

        BartenderPlayerData bartenderPlayerData = getBartenderPlayerData(playerData.get());

        boolean formulaIsNew = !bartenderPlayerData.getUnlockFormulaList().contains(bartenderOrderData.getFormulaId());
        // set proto first
        proto.setIsNew(formulaIsNew) // refer to formula not order
            .setFormulaId(bartenderOrderData.getFormulaId())
            .setFinishOrderId(bartenderOrderData.getId())
            .setQuestId(req.getQuestId())
            .addAllAffixList(bartenderOrderData.getAffixes())
            .setRetcode(Retcode.RET_SUCC_VALUE);

        // update player data
        bartenderPlayerData.getFinishedOrder().add(bartenderOrderData.getId());
        if (formulaIsNew) {
            bartenderPlayerData.getUnlockFormulaList().add(bartenderOrderData.getFormulaId());
            player.getActivityManager().triggerWatcher(WatcherTriggerType.TRIGGER_BARTENDER_UNLOCK_FORMULA);
        }        
        unlockTask(playerData.get(), bartenderPlayerData); // not the right place 
        finishTask(player, playerData.get(), bartenderPlayerData, req.getQuestId());

        playerData.get().setDetail(bartenderPlayerData);
        playerData.get().save();

        player.sendPacket(new PacketActivityInfoNotify(toProto(
            playerData.get(),
            player.getActivityManager().getConditionExecutor()
        )));

        return proto.build();
    }
}
