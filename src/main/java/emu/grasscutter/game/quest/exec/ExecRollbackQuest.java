package emu.grasscutter.game.quest.exec;

import emu.grasscutter.game.quest.GameQuest;
import emu.grasscutter.game.quest.QuestValueExec;
import emu.grasscutter.game.quest.enums.QuestExec;
import emu.grasscutter.game.quest.handlers.QuestExecHandler;
import emu.grasscutter.data.common.quest.SubQuestData.QuestExecParam;
import emu.grasscutter.server.packet.send.PacketScenePlayerLocationNotify;

@QuestValueExec(QuestExec.QUEST_EXEC_ROLLBACK_QUEST)
public class ExecRollbackQuest extends QuestExecHandler {
    @Override
    public boolean execute(GameQuest quest, QuestExecParam condition, String... paramStr) {
        var targetQuestId = Integer.parseInt(paramStr[0]);
        var targetQuest = quest.getOwner().getQuestManager().getQuestById(targetQuestId);
        var targetPosition = targetQuest.getMainQuest().rewindTo(targetQuest, true);
        if(targetPosition == null){
            return false;
        }
        quest.getOwner().getPosition().set(targetPosition.get(0));
        quest.getOwner().getRotation().set(targetPosition.get(1));
        quest.getOwner().sendPacket(new PacketScenePlayerLocationNotify(quest.getOwner().getScene()));
        // todo proper reset and warp
        return true;
    }
}
