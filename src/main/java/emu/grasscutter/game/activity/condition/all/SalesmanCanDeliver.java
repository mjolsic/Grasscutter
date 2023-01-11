package emu.grasscutter.game.activity.condition.all;

import emu.grasscutter.game.activity.ActivityConfigItem;
import emu.grasscutter.game.activity.PlayerActivityData;
import emu.grasscutter.game.activity.condition.ActivityCondition;
import emu.grasscutter.game.activity.condition.ActivityConditionBaseHandler;
import emu.grasscutter.game.activity.salesmanmp.SalesmanMpActivityHandler;

import static emu.grasscutter.game.activity.condition.ActivityConditions.NEW_ACTIVITY_COND_SALESMAN_CAN_DELIVER;

import lombok.val;

@ActivityCondition(NEW_ACTIVITY_COND_SALESMAN_CAN_DELIVER)
public class SalesmanCanDeliver extends ActivityConditionBaseHandler {
    @Override
    public boolean execute(PlayerActivityData activityData, ActivityConfigItem activityConfig, int... params) {
        SalesmanMpActivityHandler handler = (SalesmanMpActivityHandler) activityData.getActivityHandler();
        if (handler == null) return false;

        val salesmanMpPlayerData = handler.getSalesmanMpPlayerData(activityData);
        if (salesmanMpPlayerData == null) return false;
        
        return !salesmanMpPlayerData.isTodayHasDelivered();
    }
}
