package emu.grasscutter.game.quest.conditions;

import emu.grasscutter.data.excels.QuestData;
import emu.grasscutter.game.player.Player;
import emu.grasscutter.game.quest.GameQuest;
import emu.grasscutter.game.quest.QuestValueCond;

import static emu.grasscutter.game.quest.enums.QuestCond.QUEST_COND_ITEM_NUM_LESS_THAN;

@QuestValueCond(QUEST_COND_ITEM_NUM_LESS_THAN)
public class ConditionItemNumLessThan extends BaseCondition {

    @Override
    public boolean execute(Player owner, QuestData questData, QuestData.QuestAcceptCondition condition, String paramStr, int... params) {
        var checkItem = owner.getInventory().getItemByGuid(condition.getParam()[0]);
        return checkItem == null || checkItem.getCount() < condition.getParam()[1];
    }

}
