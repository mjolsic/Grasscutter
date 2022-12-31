package emu.grasscutter.game.quest.conditions;

import emu.grasscutter.Grasscutter;
import emu.grasscutter.data.GameData;
import emu.grasscutter.data.binout.MainQuestData;
import emu.grasscutter.data.excels.QuestData;
import emu.grasscutter.game.player.Player;
import emu.grasscutter.game.quest.GameMainQuest;
import emu.grasscutter.game.quest.GameQuest;
import emu.grasscutter.game.quest.QuestValueCond;
import lombok.val;

import static emu.grasscutter.game.quest.enums.QuestCond.QUEST_COND_COMPLETE_TALK;

@QuestValueCond(QUEST_COND_COMPLETE_TALK)
public class ConditionCompleteTalk extends BaseCondition {

    @Override
    public boolean execute(Player owner, QuestData questData, QuestData.QuestAcceptCondition condition, String paramStr, int... params) {
        val talkId = condition.getParam()[0];
        GameMainQuest checkMainQuest = owner.getQuestManager().getMainQuestByTalkId(talkId);
        if (checkMainQuest == null || GameData.getMainQuestDataMap().get(checkMainQuest.getParentQuestId()).getTalks() == null) {
            Grasscutter.getLogger().debug("Warning: mainQuest {} hasn't been started yet, or has no talks", condition.getParam()[0]/100);
            return false;
        }
        MainQuestData.TalkData talkData = checkMainQuest.getTalks().get(talkId);
        //TODO check should we really check the childquest
        return talkData != null || checkMainQuest.getChildQuestById(talkId) != null;
    }

}
