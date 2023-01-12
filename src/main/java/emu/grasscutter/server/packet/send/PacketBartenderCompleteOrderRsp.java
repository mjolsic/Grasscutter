package emu.grasscutter.server.packet.send;

import emu.grasscutter.net.packet.BasePacket;
import emu.grasscutter.net.packet.PacketOpcodes;
import emu.grasscutter.net.proto.BartenderCompleteOrderRspOuterClass.BartenderCompleteOrderRsp;

public class PacketBartenderCompleteOrderRsp extends BasePacket {
	
	public PacketBartenderCompleteOrderRsp(BartenderCompleteOrderRsp proto) {
		super(PacketOpcodes.BartenderCompleteOrderRsp);
		this.setData(proto);
	}

	public PacketBartenderCompleteOrderRsp(int retcodeVal) {
		super(PacketOpcodes.BartenderGetFormulaRsp);
		
		this.setData(BartenderCompleteOrderRsp.newBuilder()
			.setRetcode(retcodeVal)
			.build());
	}
}
