package emu.grasscutter.game.quest.conditions;

import emu.grasscutter.data.excels.QuestData;
import emu.grasscutter.game.player.Player;
import emu.grasscutter.game.quest.GameQuest;
import emu.grasscutter.game.quest.QuestValueCond;

import static emu.grasscutter.game.quest.enums.QuestCond.QUEST_COND_OPEN_STATE_EQUAL;
import static emu.grasscutter.game.quest.enums.QuestCond.QUEST_COND_PACK_HAVE_ITEM;

@QuestValueCond(QUEST_COND_OPEN_STATE_EQUAL)
public class ConditionOpenStateEqual extends BaseCondition {

    @Override
    public boolean execute(Player owner, QuestData questData, QuestData.QuestAcceptCondition condition, String paramStr, int... params) {
        return owner.getProgressManager().getOpenState(condition.getParam()[0]) == condition.getParam()[1];
    }

}
