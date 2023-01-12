package emu.grasscutter.game.activity.bartender;

import emu.grasscutter.data.GameData;
import emu.grasscutter.data.common.ItemParamData;
import emu.grasscutter.data.excels.BartenderFormulaData;
import emu.grasscutter.game.activity.ActivityHandler;
import emu.grasscutter.game.activity.GameActivity;
import emu.grasscutter.game.activity.PlayerActivityData;
import emu.grasscutter.game.player.Player;
import emu.grasscutter.game.props.ActivityType;
import emu.grasscutter.net.proto.ActivityInfoOuterClass;
import emu.grasscutter.net.proto.ItemParamOuterClass.ItemParam;
import emu.grasscutter.net.proto.BartenderGetFormulaRspOuterClass.BartenderGetFormulaRsp;
import emu.grasscutter.net.proto.RetcodeOuterClass.Retcode;
import emu.grasscutter.server.packet.send.PacketActivityInfoNotify;

import emu.grasscutter.utils.JsonUtils;

import static emu.grasscutter.game.quest.enums.QuestCond.QUEST_COND_ACTIVITY_COND;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@GameActivity(ActivityType.NEW_ACTIVITY_BARTENDER)
public class BartenderActivityHandler extends ActivityHandler {

    @Override
    public void onInitPlayerActivityData(PlayerActivityData playerActivityData) {
        BartenderPlayerData bartenderPlayerData = BartenderPlayerData.create();

        playerActivityData.setDetail(bartenderPlayerData);
    }

    @Override
    public void onProtoBuild(PlayerActivityData playerActivityData, ActivityInfoOuterClass.ActivityInfo.Builder activityInfo) {
        BartenderPlayerData bartenderPlayerData = getBartenderPlayerData(playerActivityData);
        activityInfo.setBartenderInfo(bartenderPlayerData.toProto());
    }

    @Override
    public void addActivityQuest(Player player) {
        // v2.5 bartender activity quests conditions
        Set<Integer> activityQuestCond = Set.of(5062001, 5062002, 5062003, 5062004, 5062005, 5062006, 5062007, 5062008, 5062009);

        // activityQuestCond.forEach(c -> player.getQuestManager().queueEvent(QUEST_COND_ACTIVITY_COND, c, 1));
    }

    public BartenderPlayerData getBartenderPlayerData(PlayerActivityData playerActivityData) {
        if (playerActivityData.getDetail() == null || playerActivityData.getDetail().isBlank()) {
            onInitPlayerActivityData(playerActivityData);
            playerActivityData.save();
        }

        return JsonUtils.decode(playerActivityData.getDetail(), BartenderPlayerData.class);
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
        BartenderPlayerData bartenderPlayerData = getBartenderPlayerData(playerData.get());

        proto.setFormulaId(bartenderFormulaData.getId())
            .addAllAffixList(bartenderFormulaData.getAffixes())
            .setIsNew(!bartenderPlayerData.getUnlockFormulaList().contains(bartenderFormulaData.getId()));

        bartenderPlayerData.getUnlockFormulaList().add(bartenderFormulaData.getId());
        playerData.get().setDetail(bartenderPlayerData);
        playerData.get().save();

        player.sendPacket(new PacketActivityInfoNotify(this.toProto(
            playerData.get(),
            player.getActivityManager().getConditionExecutor()
        )));
        return proto.build();
    }
}
