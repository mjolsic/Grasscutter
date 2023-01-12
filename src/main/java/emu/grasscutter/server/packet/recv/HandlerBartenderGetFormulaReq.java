package emu.grasscutter.server.packet.recv;

import emu.grasscutter.game.activity.bartender.BartenderActivityHandler;
import emu.grasscutter.game.props.ActivityType;
import emu.grasscutter.net.packet.Opcodes;
import emu.grasscutter.net.packet.PacketOpcodes;
import emu.grasscutter.net.packet.PacketHandler;
import emu.grasscutter.net.proto.BartenderGetFormulaReqOuterClass.BartenderGetFormulaReq;
import emu.grasscutter.net.proto.RetcodeOuterClass.Retcode;
import emu.grasscutter.server.game.GameSession;
import emu.grasscutter.server.packet.send.PacketBartenderGetFormulaRsp;

import java.util.Optional;

@Opcodes(PacketOpcodes.BartenderGetFormulaReq)
public class HandlerBartenderGetFormulaReq extends PacketHandler {
	
	@Override
	public void handle(GameSession session, byte[] header, byte[] payload) throws Exception {
		Optional<BartenderActivityHandler> handler = session.getPlayer().getActivityManager()
			.getActivityHandlerAs(ActivityType.NEW_ACTIVITY_BARTENDER, BartenderActivityHandler.class);

		if (!handler.isPresent()) {
			session.getPlayer().sendPacket(new PacketBartenderGetFormulaRsp(Retcode.RET_FAIL_VALUE));
			return;
		}

		BartenderGetFormulaReq req = BartenderGetFormulaReq.parseFrom(payload);

		session.getPlayer().sendPacket(new PacketBartenderGetFormulaRsp(
			handler.get().getFormula(session.getPlayer(), req.getQuestId(), req.getItemListList())
		));
	}

}
