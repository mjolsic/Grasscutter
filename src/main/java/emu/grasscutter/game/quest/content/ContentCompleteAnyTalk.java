package emu.grasscutter.game.quest.content;

import emu.grasscutter.data.binout.MainQuestData;
import emu.grasscutter.data.excels.QuestData;
import emu.grasscutter.game.quest.GameMainQuest;
import emu.grasscutter.game.quest.GameQuest;
import emu.grasscutter.game.quest.QuestValueContent;
import lombok.val;

import java.util.Arrays;

import static emu.grasscutter.game.quest.enums.QuestContent.QUEST_CONTENT_COMPLETE_ANY_TALK;

@QuestValueContent(QUEST_CONTENT_COMPLETE_ANY_TALK)
public class ContentCompleteAnyTalk extends BaseContent {

    @Override
    public boolean execute(GameQuest quest, QuestData.QuestContentCondition condition, String paramStr, int... params) {
        val talkId = params[0];
        return Arrays.stream(condition.getParamStr().split(","))
            .mapToInt(Integer::parseInt)
            .anyMatch(tids -> tids == talkId);
        /*GameMainQuest checkMainQuest = quest.getOwner().getQuestManager().getMainQuestById(talkId / 100);
        if (checkMainQuest == null) {
            return false;
        }
        MainQuestData.TalkData talkData = checkMainQuest.getTalks().get(talkId);
        return talkData == null || condition.getParamStr().contains(paramStr) || checkMainQuest.getChildQuestById(params[0]) != null;*/
    }

}
