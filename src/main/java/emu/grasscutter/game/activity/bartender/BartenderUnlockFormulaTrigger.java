package emu.grasscutter.game.activity.bartender;

import emu.grasscutter.data.GameData;
import emu.grasscutter.game.activity.ActivityWatcher;
import emu.grasscutter.game.activity.ActivityWatcherType;
import emu.grasscutter.game.props.WatcherTriggerType;

@ActivityWatcherType(WatcherTriggerType.TRIGGER_BARTENDER_UNLOCK_FORMULA)
public class BartenderUnlockFormulaTrigger extends ActivityWatcher {
    @Override
    protected boolean isMeet(String... param) {
        // since this watcher dont take parameter, i'll return true here
        // bartender handler will handle checking for new formula 
        return true;
    }
}
