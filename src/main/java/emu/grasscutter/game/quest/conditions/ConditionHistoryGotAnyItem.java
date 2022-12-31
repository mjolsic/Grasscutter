package emu.grasscutter.game.quest.conditions;

import emu.grasscutter.data.excels.QuestData;
import emu.grasscutter.game.player.Player;
import emu.grasscutter.game.quest.GameQuest;
import emu.grasscutter.game.quest.QuestValueCond;

import static emu.grasscutter.game.quest.enums.QuestCond.*;

@QuestValueCond(QUEST_COND_HISTORY_GOT_ANY_ITEM)
public class ConditionHistoryGotAnyItem extends BaseCondition {

    @Override
    public boolean execute(Player owner, QuestData questData, QuestData.QuestAcceptCondition condition, String paramStr, int... params) {
        // todo only works if the item is still in the inventory, so no real history check
        return owner.getInventory().getItemByGuid(condition.getParam()[0]) != null;
    }

}
