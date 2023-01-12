package emu.grasscutter.server.packet.send;

import emu.grasscutter.net.packet.BasePacket;
import emu.grasscutter.net.packet.PacketOpcodes;
import emu.grasscutter.net.proto.BartenderGetFormulaRspOuterClass.BartenderGetFormulaRsp;

public class PacketBartenderGetFormulaRsp extends BasePacket {
	
	public PacketBartenderGetFormulaRsp(BartenderGetFormulaRsp proto) {
		super(PacketOpcodes.BartenderGetFormulaRsp);
		this.setData(proto);
	}

	public PacketBartenderGetFormulaRsp(int retcodeVal) {
		super(PacketOpcodes.BartenderGetFormulaRsp);

		this.setData(BartenderGetFormulaRsp.newBuilder()
			.setRetcode(retcodeVal)
			.build());
	}
}
