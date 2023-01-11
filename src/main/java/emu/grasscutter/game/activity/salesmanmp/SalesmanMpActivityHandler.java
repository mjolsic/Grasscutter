package emu.grasscutter.game.activity.salesmanmp;

import emu.grasscutter.Grasscutter;
import emu.grasscutter.data.GameData;
import emu.grasscutter.data.common.ItemParamData;
import emu.grasscutter.data.excels.ActivitySalesmanData;
import emu.grasscutter.data.excels.ActivitySalesmanDailyData;
import emu.grasscutter.data.excels.RewardData;
import emu.grasscutter.game.activity.ActivityHandler;
import emu.grasscutter.game.activity.GameActivity;
import emu.grasscutter.game.activity.PlayerActivityData;
import emu.grasscutter.game.inventory.GameItem;
import emu.grasscutter.game.player.Player;
import emu.grasscutter.game.props.ActivityType;
import emu.grasscutter.game.props.ActionReason;
import emu.grasscutter.net.proto.ActivityInfoOuterClass.ActivityInfo;
import emu.grasscutter.net.proto.RetcodeOuterClass.Retcode;
import emu.grasscutter.net.proto.SalesmanActivityDetailInfoOuterClass.SalesmanActivityDetailInfo;
import emu.grasscutter.server.packet.send.PacketActivityInfoNotify;
import emu.grasscutter.utils.JsonUtils;

import java.util.Optional;
import java.util.stream.Collectors;

@GameActivity(ActivityType.NEW_ACTIVITY_SALESMAN_MP)
public class SalesmanMpActivityHandler extends ActivityHandler {

    @Override
    public void onInitPlayerActivityData(PlayerActivityData playerActivityData) {
        SalesmanMpPlayerData salesmanMpPlayerData = SalesmanMpPlayerData.create(getActivityConfigItem().getScheduleId());

        playerActivityData.setDetail(salesmanMpPlayerData);
    }

    @Override
    public void onProtoBuild(PlayerActivityData playerActivityData, ActivityInfo.Builder activityInfo) {
        SalesmanMpPlayerData salesmanMpPlayerData = getSalesmanMpPlayerData(playerActivityData);

        activityInfo.setSalesmanInfo(salesmanMpPlayerData.toProto());
    }

    public SalesmanMpPlayerData getSalesmanMpPlayerData(PlayerActivityData playerActivityData) {
        if (playerActivityData.getDetail() == null || playerActivityData.getDetail().isBlank()) {
            onInitPlayerActivityData(playerActivityData);
            playerActivityData.save();
        }

        return JsonUtils.decode(playerActivityData.getDetail(), SalesmanMpPlayerData.class);
    }

    public int getTodayRewardId(Player player, PlayerActivityData playerActivityData) {
        SalesmanMpPlayerData salesmanPlayerData = getSalesmanMpPlayerData(playerActivityData);
        if (salesmanPlayerData.start()) {
            playerActivityData.setDetail(salesmanPlayerData);
            playerActivityData.save();
            player.sendPacket(new PacketActivityInfoNotify(player.getActivityManager()
                .getInfoProtoByActivityType(ActivityType.NEW_ACTIVITY_SALESMAN_MP).get()));
        }
        return salesmanPlayerData.getDayRewardId();
    }

    public int deliverItem(Player player, PlayerActivityData playerActivityData, int scheduleId) {
        SalesmanMpPlayerData salesmanPlayerData = getSalesmanMpPlayerData(playerActivityData);
        if (salesmanPlayerData.isTodayHasDelivered()) return Retcode.RET_SALESMAN_ALREADY_DELIVERED_VALUE;
        if (getActivityConfigItem().getScheduleId() != scheduleId) return Retcode.RET_FAIL_VALUE;

        ActivitySalesmanData salesmanExcelData = GameData.getActivitySalesmanDataMap().get(scheduleId);
        int dailyConfig = salesmanExcelData.getDailyConfigIdList().get(salesmanPlayerData.getDayIndex()-1);
        ActivitySalesmanDailyData salesmanDailyData = GameData.getActivitySalesmanDailyDataMap().get(dailyConfig);
        Grasscutter.getLogger().info("{}", dailyConfig);
        if (salesmanDailyData == null) return Retcode.RET_FAIL_VALUE;

        RewardData rewardData = GameData.getRewardDataMap().get(salesmanPlayerData.getDayRewardId());
        if (rewardData == null) return Retcode.RET_FAIL_VALUE;

        // check if player has sufficient item to pay
        for (ItemParamData costItem : salesmanDailyData.getCostItemList()) {
            if (player.getInventory().getVirtualItemCount(costItem.getId()) < costItem.getCount()) 
                return Retcode.RET_FAIL_VALUE;
        }
        
        salesmanDailyData.getCostItemList().forEach(itemParamData -> player.getInventory().payItem(itemParamData));

        player.getInventory().addItemParamDatas(rewardData.getRewardItemList(), ActionReason.SalesmanReward);

        salesmanPlayerData.deliver();
        playerActivityData.setDetail(salesmanPlayerData);
        playerActivityData.save();
        player.sendPacket(new PacketActivityInfoNotify(player.getActivityManager()
            .getInfoProtoByActivityType(ActivityType.NEW_ACTIVITY_SALESMAN_MP).get()));

        return Retcode.RET_SUCC_VALUE;
    }
}
