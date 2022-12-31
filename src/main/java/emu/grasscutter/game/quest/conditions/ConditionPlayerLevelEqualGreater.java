package emu.grasscutter.game.quest.conditions;

import emu.grasscutter.data.excels.QuestData;
import emu.grasscutter.game.player.Player;
import emu.grasscutter.game.quest.GameQuest;
import emu.grasscutter.game.quest.QuestValueCond;

import static emu.grasscutter.game.quest.enums.QuestCond.QUEST_COND_PLAYER_LEVEL_EQUAL_GREATER;

@QuestValueCond(QUEST_COND_PLAYER_LEVEL_EQUAL_GREATER)
public class ConditionPlayerLevelEqualGreater extends BaseCondition {
    // TODO find out what param[1] is used for, e.g. 2501001
    @Override
    public boolean execute(Player owner, QuestData questData, QuestData.QuestAcceptCondition condition, String paramStr, int... params) {
        return owner.getLevel() >= condition.getParam()[0];
    }

}
