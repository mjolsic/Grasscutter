package emu.grasscutter.game.activity.bartender;

import emu.grasscutter.data.GameData;
import emu.grasscutter.game.activity.ActivityWatcher;
import emu.grasscutter.game.activity.ActivityWatcherType;
import emu.grasscutter.game.activity.PlayerActivityData;
import emu.grasscutter.game.props.WatcherTriggerType;

import java.util.stream.Stream;

@ActivityWatcherType(WatcherTriggerType.TRIGGER_BARTENDER_FINISH_STORY_MODULE)
public class BartenderFinishStoryModuleTrigger extends ActivityWatcher {
    @Override
    protected boolean isMeet(String... param) {
        if (param.length < 1) return false;

        return Stream.of(getActivityWatcherData().getTriggerConfig().getParamList().get(0).split(","))
            .toList().contains(param[0]);
    }
}
