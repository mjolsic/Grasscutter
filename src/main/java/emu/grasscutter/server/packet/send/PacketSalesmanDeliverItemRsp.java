package emu.grasscutter.server.packet.send;

import emu.grasscutter.net.packet.BasePacket;
import emu.grasscutter.net.packet.PacketOpcodes;
import emu.grasscutter.net.proto.SalesmanDeliverItemRspOuterClass.SalesmanDeliverItemRsp;

public class PacketSalesmanDeliverItemRsp extends BasePacket {
	
	public PacketSalesmanDeliverItemRsp(int scheduleId, int retcodeVal) {
		super(PacketOpcodes.SalesmanDeliverItemRsp);
		this.setData(SalesmanDeliverItemRsp.newBuilder()
			.setScheduleId(scheduleId)
			.setRetcode(retcodeVal)
			.build());
	}
}
