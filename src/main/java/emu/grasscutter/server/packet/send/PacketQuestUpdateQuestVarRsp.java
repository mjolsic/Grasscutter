package emu.grasscutter.server.packet.send;

import emu.grasscutter.net.packet.BasePacket;
import emu.grasscutter.net.packet.Opcodes;
import emu.grasscutter.net.packet.PacketOpcodes;
import emu.grasscutter.net.proto.QuestUpdateQuestVarRspOuterClass.QuestUpdateQuestVarRsp;
import emu.grasscutter.net.proto.RetcodeOuterClass;
import lombok.val;

@Opcodes(PacketOpcodes.QuestUpdateQuestVarReq)
public class PacketQuestUpdateQuestVarRsp extends BasePacket {


    public PacketQuestUpdateQuestVarRsp(int questId) {
        this(questId, RetcodeOuterClass.Retcode.RET_SUCC_VALUE);
    }

    public PacketQuestUpdateQuestVarRsp(int questId, int result) {
        super(PacketOpcodes.QuestUpdateQuestVarRsp);
        val rsp = QuestUpdateQuestVarRsp.newBuilder()
            .setQuestId(questId)
            .setRetcode(result);
        this.setData(rsp);
    }
}
