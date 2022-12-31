package emu.grasscutter.game.quest.conditions;

import emu.grasscutter.Grasscutter;
import emu.grasscutter.data.excels.QuestData;
import emu.grasscutter.game.player.Player;
import emu.grasscutter.game.quest.GameQuest;
import emu.grasscutter.game.quest.QuestValueCond;
import lombok.val;

import static emu.grasscutter.game.quest.enums.QuestCond.QUEST_COND_QUEST_VAR_EQUAL;

public abstract class BaseConditionQuestVar extends BaseCondition {

    protected int getQuestVar(Player owner, QuestData questData, int index){
        val mainQuest = owner.getQuestManager().getMainQuestById(questData.getMainId());
        if(mainQuest == null){
            Grasscutter.getLogger().debug("mainQuest for quest var not available yet");
            return -1;
        }
        var questVars = mainQuest.getQuestVars();
        if (index >= questVars.length) {
            Grasscutter.getLogger().error("questVar out of bounds for {} index {} size {}", questData.getSubId(), index, questVars.length);
            return -2;
        }
        return questVars[index];
    }
}
