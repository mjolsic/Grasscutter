package emu.grasscutter.server.packet.recv;

import emu.grasscutter.net.packet.Opcodes;
import emu.grasscutter.net.packet.PacketOpcodes;
import emu.grasscutter.net.packet.PacketHandler;
import emu.grasscutter.server.packet.send.PacketBartenderCancelOrderRsp;
import emu.grasscutter.net.proto.RetcodeOuterClass.Retcode;
import emu.grasscutter.net.proto.BartenderCancelOrderReqOuterClass.BartenderCancelOrderReq;
import emu.grasscutter.server.game.GameSession;


@Opcodes(PacketOpcodes.BartenderCancelOrderReq)
public class HandlerBartenderCancelOrderReq extends PacketHandler {
	
	@Override
	public void handle(GameSession session, byte[] header, byte[] payload) throws Exception {
		BartenderCancelOrderReq req = BartenderCancelOrderReq.parseFrom(payload);

		session.getPlayer().sendPacket(new PacketBartenderCancelOrderRsp(req.getQuestId(), Retcode.RET_SUCC_VALUE));
	}

}
