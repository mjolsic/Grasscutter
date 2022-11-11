package emu.grasscutter.game.quest.content;

import emu.grasscutter.data.excels.QuestData;
import emu.grasscutter.game.quest.GameQuest;
import emu.grasscutter.game.quest.QuestValueContent;

import static emu.grasscutter.game.quest.enums.QuestContent.QUEST_CONTENT_ADD_QUEST_PROGRESS;

@QuestValueContent(QUEST_CONTENT_ADD_QUEST_PROGRESS)
public class ContentAddQuestProgress extends BaseContent {

    @Override
    public boolean execute(GameQuest quest, QuestData.QuestContentCondition condition, String paramStr, int... params) {
        // param[0] have nothing to do with the lua group I think, it comes from EXEC_ADD_PROGRESS
        // if the condition count is 0 I think it is safe to assume that the
        // condition count from EXEC only needs to be 1
        int count = condition.getCount() > 0 ? condition.getCount() : 1;
        if (quest.getOwner().getQuestProgressCountMap().get(params[0]) == null
            || quest.getOwner().getQuestProgressCountMap().get(params[0]) < count){
                return false;
        }
        return true;
    }

}
