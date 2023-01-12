package emu.grasscutter.server.packet.send;

import emu.grasscutter.net.packet.BasePacket;
import emu.grasscutter.net.packet.PacketOpcodes;
import emu.grasscutter.net.proto.BartenderCancelOrderRspOuterClass.BartenderCancelOrderRsp;

public class PacketBartenderCancelOrderRsp extends BasePacket {
	
	public PacketBartenderCancelOrderRsp(int questId, int retcodeVal) {
		super(PacketOpcodes.BartenderCancelOrderRsp);
		this.setData(BartenderCancelOrderRsp.newBuilder()
			.setQuestId(questId)
			.setRetcode(retcodeVal)
			.build());
	}
}
