package emu.grasscutter.game.activity.condition.all;

import emu.grasscutter.game.activity.ActivityConfigItem;
import emu.grasscutter.game.activity.PlayerActivityData;
import emu.grasscutter.game.activity.PlayerActivityData.WatcherInfo;
import emu.grasscutter.game.activity.condition.ActivityCondition;
import emu.grasscutter.game.activity.condition.ActivityConditionBaseHandler;

import static emu.grasscutter.game.activity.condition.ActivityConditions.NEW_ACTIVITY_COND_FINISH_WATCHER;

@ActivityCondition(NEW_ACTIVITY_COND_FINISH_WATCHER)
public class FinishWatcher extends ActivityConditionBaseHandler {
    @Override
    public boolean execute(PlayerActivityData activityData, ActivityConfigItem activityConfig, int... params) {
        WatcherInfo watcherInfo = activityData.getWatcherInfoMap().get(params[0]);
        return watcherInfo != null && watcherInfo.getCurProgress() >= watcherInfo.getTotalProgress();
    }
}
