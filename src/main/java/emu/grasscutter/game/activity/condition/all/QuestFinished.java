package emu.grasscutter.game.activity.condition.all;

import emu.grasscutter.game.activity.ActivityConfigItem;
import emu.grasscutter.game.activity.PlayerActivityData;
import emu.grasscutter.game.activity.condition.ActivityCondition;
import emu.grasscutter.game.activity.condition.ActivityConditionBaseHandler;
import emu.grasscutter.game.quest.QuestManager;
import emu.grasscutter.game.quest.enums.QuestState;

import static emu.grasscutter.game.activity.condition.ActivityConditions.NEW_ACTIVITY_COND_QUEST_FINISH;

import java.util.stream.IntStream;

@ActivityCondition(NEW_ACTIVITY_COND_QUEST_FINISH)
public class QuestFinished extends ActivityConditionBaseHandler {
    @Override
    public boolean execute(PlayerActivityData activityData, ActivityConfigItem activityConfig, int... params) {
        QuestManager questManager = activityData.getPlayer().getQuestManager();
        // can have multiple parameters to check
        return IntStream.of(params).allMatch(q -> questManager.getQuestById(q) != null
            && questManager.getQuestById(q).getState() == QuestState.QUEST_STATE_FINISHED);
    }
}
