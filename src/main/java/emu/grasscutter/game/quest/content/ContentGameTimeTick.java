package emu.grasscutter.game.quest.content;

import emu.grasscutter.data.excels.QuestData;
import emu.grasscutter.game.quest.GameQuest;
import emu.grasscutter.game.quest.QuestValueContent;
import lombok.val;

import static emu.grasscutter.game.quest.enums.QuestContent.QUEST_CONTENT_GAME_TIME_TICK;

@QuestValueContent(QUEST_CONTENT_GAME_TIME_TICK)
public class ContentGameTimeTick extends BaseContent {

    @Override
    public boolean execute(GameQuest quest, QuestData.QuestContentCondition condition, String paramStr, int... params) {
        val daysSinceStart = quest.getOwner().getWorld().getGameTimeDays() - quest.getStartGameDay();
        val currentHour = quest.getOwner().getWorld().getGameTimeHours();

        // params[0] is days since start, str is hours of day
        val range = condition.getParamStr().split(",");
        val from = Integer.parseInt(range[0]);
        val to = Integer.parseInt(range[1]);

        val daysToPass = condition.getParam()[0];

        val isTimeMeet = from < to ? currentHour >= from && currentHour <= to
            : currentHour <= to || currentHour >= from;

        val isDaysSinceMet =  daysSinceStart >= daysToPass;

        return isTimeMeet && isDaysSinceMet;
    }

}
