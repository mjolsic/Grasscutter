package emu.grasscutter.game.quest.exec;

import emu.grasscutter.game.quest.GameQuest;
import emu.grasscutter.game.quest.QuestValueExec;
import emu.grasscutter.game.quest.enums.QuestExec;
import emu.grasscutter.game.quest.handlers.QuestExecHandler;
import emu.grasscutter.data.common.quest.SubQuestData.QuestExecParam;
import lombok.val;

@QuestValueExec(QuestExec.QUEST_EXEC_CLEAR_TIME_VAR)
public class ExecClearTimeVar extends QuestExecHandler {
    @Override
    public boolean execute(GameQuest quest, QuestExecParam condition, String... paramStr) {
        val mainQuestId = Integer.parseInt(condition.getParam()[0]);
        val timeVarId = Integer.parseInt(condition.getParam()[1]);
        val mainQuest = quest.getOwner().getQuestManager().getMainQuestById(mainQuestId);
        return mainQuest.clearTimeVar(timeVarId);
    }
}
