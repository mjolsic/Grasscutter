package emu.grasscutter.server.packet.recv;

import emu.grasscutter.data.GameData;
import emu.grasscutter.net.packet.Opcodes;
import emu.grasscutter.net.packet.PacketHandler;
import emu.grasscutter.net.packet.PacketOpcodes;
import emu.grasscutter.net.proto.RetcodeOuterClass;
import emu.grasscutter.net.proto.UnlockPersonalLineReqOuterClass;
import emu.grasscutter.server.game.GameSession;
import emu.grasscutter.server.packet.send.PacketUnlockPersonalLineRsp;
import lombok.val;

@Opcodes(PacketOpcodes.UnlockPersonalLineReq)
public class HandlerUnlockPersonalLineReq extends PacketHandler {

	@Override
	public void handle(GameSession session, byte[] header, byte[] payload) throws Exception {
        val req = UnlockPersonalLineReqOuterClass.UnlockPersonalLineReq.parseFrom(payload);
        var data = GameData.getPersonalLineDataMap().get(req.getPersonalLineId());
        if(data == null){
            session.send(new PacketUnlockPersonalLineRsp(req.getPersonalLineId(), RetcodeOuterClass.Retcode.RET_FAIL));
            return;
        }

        //TODO check conditions

        session.getPlayer().addPersonalLine(data.getId());
        session.getPlayer().useLegendaryKey(1);

        session.send(new PacketUnlockPersonalLineRsp(data.getId(), 1, data.getChapterId()));
	}

}
