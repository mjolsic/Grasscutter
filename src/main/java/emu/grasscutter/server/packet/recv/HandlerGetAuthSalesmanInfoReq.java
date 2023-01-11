package emu.grasscutter.server.packet.recv;

import emu.grasscutter.data.GameData;
import emu.grasscutter.data.excels.ActivitySalesmanData;
import emu.grasscutter.game.activity.PlayerActivityData;
import emu.grasscutter.game.activity.salesmanmp.SalesmanMpActivityHandler;
import emu.grasscutter.game.activity.salesmanmp.SalesmanMpPlayerData;
import emu.grasscutter.game.props.ActivityType;
import emu.grasscutter.net.packet.Opcodes;
import emu.grasscutter.net.packet.PacketOpcodes;
import emu.grasscutter.net.packet.PacketHandler;
import emu.grasscutter.server.game.GameSession;
import emu.grasscutter.net.proto.GetAuthSalesmanInfoReqOuterClass.GetAuthSalesmanInfoReq;
import emu.grasscutter.net.proto.RetcodeOuterClass.Retcode;
import emu.grasscutter.server.packet.send.PacketGetAuthSalesmanInfoRsp;

import java.util.Optional;

@Opcodes(PacketOpcodes.GetAuthSalesmanInfoReq)
public class HandlerGetAuthSalesmanInfoReq extends PacketHandler {
	
	@Override
	public void handle(GameSession session, byte[] header, byte[] payload) throws Exception {
		GetAuthSalesmanInfoReq req = GetAuthSalesmanInfoReq.parseFrom(payload);

		ActivitySalesmanData scheduleData = GameData.getActivitySalesmanDataMap().get(req.getScheduleId());

		Optional<PlayerActivityData> playerData = session.getPlayer().getActivityManager()
			.getPlayerActivityDataByActivityType(ActivityType.NEW_ACTIVITY_SALESMAN_MP);

		if (scheduleData == null || !playerData.isPresent()) {
			session.getPlayer().sendPacket(new PacketGetAuthSalesmanInfoRsp(req.getScheduleId(), 0, Retcode.RET_FAIL_VALUE));
			return;
		}

		SalesmanMpActivityHandler handler = (SalesmanMpActivityHandler) playerData.get().getActivityHandler();

		session.getPlayer().sendPacket(new PacketGetAuthSalesmanInfoRsp(
			req.getScheduleId(),
			handler.getTodayRewardId(session.getPlayer(), playerData.get()),
			Retcode.RET_SUCC_VALUE
		));
	}

}
