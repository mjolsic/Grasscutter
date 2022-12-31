package emu.grasscutter.game.quest.exec;

import emu.grasscutter.data.excels.QuestData;
import emu.grasscutter.game.quest.GameQuest;
import emu.grasscutter.game.quest.QuestValueExec;
import emu.grasscutter.game.quest.enums.QuestExec;
import emu.grasscutter.game.quest.handlers.QuestExecHandler;
import emu.grasscutter.server.packet.send.PacketScenePointUnlockNotify;

import java.util.stream.Stream;
import java.util.List;

@QuestValueExec(QuestExec.QUEST_EXEC_UNLOCK_POINT)
public class ExecUnlockPoint extends QuestExecHandler {
    @Override
    public boolean execute(GameQuest quest, QuestData.QuestExecParam condition, String... paramStr) {
        // Unlock the trans point for the player.
        int sceneId = Integer.parseInt(paramStr[0]);
        List<Integer> pointIds = Stream.of(paramStr[1].split(",")).map(Integer::parseInt).toList();

        pointIds.forEach(pointId -> {
            quest.getOwner().getProgressManager().unlockTransPoint(sceneId, pointId);
        });
        // Done.
        return true;
    }
}
