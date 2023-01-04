package emu.grasscutter.game.quest.conditions;

import emu.grasscutter.Grasscutter;
import emu.grasscutter.data.GameData;
import emu.grasscutter.data.excels.QuestData;
import emu.grasscutter.game.quest.GameQuest;
import emu.grasscutter.game.quest.QuestValueCond;
import lombok.val;

import static emu.grasscutter.game.quest.enums.QuestCond.QUEST_COND_COMPLETE_TALK;

@QuestValueCond(QUEST_COND_COMPLETE_TALK)
public class ConditionCompleteTalk extends BaseCondition {

    @Override
    public boolean execute(GameQuest quest, QuestData.QuestAcceptCondition condition, String paramStr, int... params) {
        val talkId = condition.getParam()[0];
        val checkMainQuest = quest.getOwner().getQuestManager().getMainQuestByTalkId(talkId);
        if (checkMainQuest == null || GameData.getMainQuestDataMap().get(checkMainQuest.getParentQuestId()).getTalks() == null) {
            Grasscutter.getLogger().debug("Warning: mainQuest {} hasn't been started yet, or has no talks", talkId / 100);
            return false;
        }
        val talkData = checkMainQuest.getTalks().get(talkId);
        return talkData != null || checkMainQuest.getChildQuestById(talkId) != null;
    }

}
