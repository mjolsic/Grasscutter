package emu.grasscutter.server.packet.send;

import emu.grasscutter.net.packet.BasePacket;
import emu.grasscutter.net.packet.PacketOpcodes;
import emu.grasscutter.net.proto.PlayerSetPauseRspOuterClass.PlayerSetPauseRsp;
import emu.grasscutter.net.proto.RetcodeOuterClass;

public class PacketPlayerSetPauseRsp extends BasePacket {

	public PacketPlayerSetPauseRsp() {
		super(PacketOpcodes.PlayerSetPauseRsp);


		this.setData(PlayerSetPauseRsp.newBuilder()
            .setRetcode(RetcodeOuterClass.Retcode.RET_SUCC_VALUE));
	}
}
