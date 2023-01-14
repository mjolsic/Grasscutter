package emu.grasscutter.game.activity.condition.all;

import emu.grasscutter.game.activity.ActivityConfigItem;
import emu.grasscutter.game.activity.PlayerActivityData;
import emu.grasscutter.game.activity.bartender.BartenderActivityHandler;
import emu.grasscutter.game.activity.bartender.BartenderPlayerData;
import emu.grasscutter.game.activity.bartender.BartenderPlayerData.LevelInfoItem;
import emu.grasscutter.game.activity.condition.ActivityCondition;
import emu.grasscutter.game.activity.condition.ActivityConditionBaseHandler;

import static emu.grasscutter.game.activity.condition.ActivityConditions.NEW_ACTIVITY_COND_FINISH_BARTENDER_LEVEL;

import java.util.Map;

@ActivityCondition(NEW_ACTIVITY_COND_FINISH_BARTENDER_LEVEL)
public class FinishBartenderLevel extends ActivityConditionBaseHandler {
    @Override
    public boolean execute(PlayerActivityData activityData, ActivityConfigItem activityConfig, int... params) {
        BartenderActivityHandler handler = (BartenderActivityHandler) activityData.getActivityHandler();
        if (handler == null) return false;

        LevelInfoItem levelInfo = handler.getBartenderPlayerData(activityData).getUnlockLevel().get(params[0]);
        
        return levelInfo != null && levelInfo.isFinish();
    }
}
