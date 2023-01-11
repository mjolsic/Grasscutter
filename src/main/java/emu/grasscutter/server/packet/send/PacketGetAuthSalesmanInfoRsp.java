package emu.grasscutter.server.packet.send;

import emu.grasscutter.net.packet.BasePacket;
import emu.grasscutter.net.packet.PacketOpcodes;
import emu.grasscutter.net.proto.GetAuthSalesmanInfoRspOuterClass.GetAuthSalesmanInfoRsp;

public class PacketGetAuthSalesmanInfoRsp extends BasePacket {
	
	public PacketGetAuthSalesmanInfoRsp(int scheduleId, int dayRewardId, int retcode) {
		super(PacketOpcodes.GetAuthSalesmanInfoRsp);

		this.setData(GetAuthSalesmanInfoRsp.newBuilder()
			.setScheduleId(scheduleId)
			.setDayRewardId(dayRewardId)
			.setRetcode(retcode)
			.build());
	}
}
