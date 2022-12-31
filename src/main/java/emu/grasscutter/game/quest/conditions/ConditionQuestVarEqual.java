package emu.grasscutter.game.quest.conditions;

import emu.grasscutter.Grasscutter;
import emu.grasscutter.data.excels.QuestData;
import emu.grasscutter.game.player.Player;
import emu.grasscutter.game.quest.GameQuest;
import emu.grasscutter.game.quest.QuestValueCond;
import lombok.val;

import static emu.grasscutter.game.quest.enums.QuestCond.QUEST_COND_QUEST_VAR_EQUAL;

@QuestValueCond(QUEST_COND_QUEST_VAR_EQUAL)
public class ConditionQuestVarEqual extends BaseConditionQuestVar {

    @Override
    public boolean execute(Player owner, QuestData questData, QuestData.QuestAcceptCondition condition, String paramStr, int... params) {
        var index = condition.getParam()[0];
        int questVarValue = getQuestVar(owner, questData, index);
        Grasscutter.getLogger().debug("questVar {} : {}", index, questVarValue);
        if(questVarValue < 0){
            return false;
        }
        return questVarValue == condition.getParam()[1];
    }
}
