package emu.grasscutter.server.packet.send;

import emu.grasscutter.net.packet.BasePacket;
import emu.grasscutter.net.packet.PacketOpcodes;
import emu.grasscutter.net.proto.SalesmanTakeSpecialRewardRspOuterClass.SalesmanTakeSpecialRewardRsp;

public class PacketSalesmanTakeSpecialRewardRsp extends BasePacket {
	
	public PacketSalesmanTakeSpecialRewardRsp(int scheduleId, int retcodeVal) {
		super(PacketOpcodes.SalesmanTakeSpecialRewardRsp);
		this.setData(SalesmanTakeSpecialRewardRsp.newBuilder()
			.setScheduleId(scheduleId)
			.setRetcode(retcodeVal)
			.build());
	}
}
