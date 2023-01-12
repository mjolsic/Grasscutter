package emu.grasscutter.server.packet.recv;

import emu.grasscutter.game.activity.bartender.BartenderActivityHandler;
import emu.grasscutter.game.props.ActivityType;
import emu.grasscutter.net.packet.Opcodes;
import emu.grasscutter.net.packet.PacketOpcodes;
import emu.grasscutter.net.packet.PacketHandler;
import emu.grasscutter.net.proto.BartenderCompleteOrderReqOuterClass.BartenderCompleteOrderReq;
import emu.grasscutter.net.proto.RetcodeOuterClass.Retcode;
import emu.grasscutter.server.game.GameSession;
import emu.grasscutter.server.packet.send.PacketBartenderCompleteOrderRsp;


import java.util.Optional;

@Opcodes(PacketOpcodes.BartenderCompleteOrderReq)
public class HandlerBartenderCompleteOrderReq extends PacketHandler {
	
	@Override
	public void handle(GameSession session, byte[] header, byte[] payload) throws Exception {
		Optional<BartenderActivityHandler> handler = session.getPlayer().getActivityManager()
			.getActivityHandlerAs(ActivityType.NEW_ACTIVITY_BARTENDER, BartenderActivityHandler.class);

		if (!handler.isPresent()) {
			session.getPlayer().sendPacket(new PacketBartenderCompleteOrderRsp(Retcode.RET_FAIL_VALUE));
			return;
		}

		BartenderCompleteOrderReq req = BartenderCompleteOrderReq.parseFrom(payload);

		session.getPlayer().sendPacket(new PacketBartenderCompleteOrderRsp(
			handler.get().completeOrder(session.getPlayer(), req)
		));
	}

}
