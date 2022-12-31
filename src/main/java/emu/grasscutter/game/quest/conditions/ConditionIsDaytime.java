package emu.grasscutter.game.quest.conditions;

import emu.grasscutter.data.excels.QuestData;
import emu.grasscutter.game.player.Player;
import emu.grasscutter.game.quest.QuestValueCond;

import static emu.grasscutter.game.quest.enums.QuestCond.*;

@QuestValueCond(QUEST_COND_IS_DAYTIME)
public class ConditionIsDaytime extends BaseCondition {

	public boolean execute(Player owner, QuestData questData, QuestData.QuestAcceptCondition condition, String paramStr, int... params) {
		return condition.getParam()[0] == params[0];
	}

}
