package emu.grasscutter.game.quest.conditions;

import emu.grasscutter.data.excels.QuestData;
import emu.grasscutter.game.player.Player;
import emu.grasscutter.game.quest.GameQuest;
import emu.grasscutter.game.quest.QuestValueCond;

import static emu.grasscutter.game.quest.enums.QuestCond.QUEST_COND_STATE_NOT_EQUAL;

@QuestValueCond(QUEST_COND_STATE_NOT_EQUAL)
public class ConditionStateNotEqual extends BaseCondition {

    @Override
    public boolean execute(Player owner, QuestData questData, QuestData.QuestAcceptCondition condition, String paramStr, int... params) {
        GameQuest checkQuest = owner.getQuestManager().getQuestById(condition.getParam()[0]);
        int questState = checkQuest == null ? 0 : checkQuest.getState().getValue();
        
        return questState != condition.getParam()[1];
    }

}
