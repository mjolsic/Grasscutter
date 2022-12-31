package emu.grasscutter.server.packet.recv;

import emu.grasscutter.Grasscutter;
import emu.grasscutter.data.GameData;
import emu.grasscutter.game.quest.GameMainQuest;
import emu.grasscutter.net.packet.Opcodes;
import emu.grasscutter.net.packet.PacketHandler;
import emu.grasscutter.net.packet.PacketOpcodes;
import emu.grasscutter.net.proto.*;
import emu.grasscutter.server.game.GameSession;
import emu.grasscutter.server.packet.send.PacketPlayerSetPauseRsp;
import emu.grasscutter.server.packet.send.PacketQuestUpdateQuestVarRsp;
import lombok.val;

import java.util.List;

@Opcodes(PacketOpcodes.QuestUpdateQuestVarReq)
public class HandlerQuestUpdateQuestVarReq extends PacketHandler {

    @Override
    public void handle(GameSession session, byte[] header, byte[] payload) throws Exception {
        //Client sends packets. One with the value, and one with the index and the new value to set/inc/dec
        val req = QuestUpdateQuestVarReqOuterClass.QuestUpdateQuestVarReq.parseFrom(payload);
        GameMainQuest mainQuest = session.getPlayer().getQuestManager().getMainQuestBySubQuestId(req.getQuestId());
        if(mainQuest == null){
            Grasscutter.getLogger().error("trying to set quest var for unknown quest {}", req.getQuestId());
            session.send(new PacketQuestUpdateQuestVarRsp(req.getQuestId(), RetcodeOuterClass.Retcode.RET_QUEST_NOT_EXIST_VALUE));
            return;
        }
        List<QuestVarOpOuterClass.QuestVarOp> questVars = req.getQuestVarOpListList();
        if (mainQuest.getQuestVarsUpdate().size() == 0) {
            for (QuestVarOpOuterClass.QuestVarOp questVar : questVars) {
                mainQuest.getQuestVarsUpdate().add(questVar.getValue());
            }
        } else {
            for (QuestVarOpOuterClass.QuestVarOp questVar : questVars) {
                if (questVar.getIsAdd()) {
                    if (questVar.getValue() >= 0) {
                        mainQuest.incQuestVar(questVar.getIndex(), questVar.getValue());
                    } else {
                        mainQuest.decQuestVar(questVar.getIndex(), questVar.getValue());
                    }
                } else {
                    mainQuest.setQuestVar(questVar.getIndex(), mainQuest.getQuestVarsUpdate().get(0));
                }
                //remove the first element from the update list
                mainQuest.getQuestVarsUpdate().remove(0);
            }
        }
        session.send(new PacketQuestUpdateQuestVarRsp(req.getQuestId()));
    }

}
