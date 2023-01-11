package emu.grasscutter.server.packet.recv;

import emu.grasscutter.data.GameData;
import emu.grasscutter.data.excels.ActivitySalesmanData;
import emu.grasscutter.game.activity.PlayerActivityData;
import emu.grasscutter.game.activity.salesmanmp.SalesmanMpActivityHandler;
import emu.grasscutter.game.props.ActivityType;

import emu.grasscutter.net.packet.Opcodes;
import emu.grasscutter.net.packet.PacketOpcodes;
import emu.grasscutter.net.packet.PacketHandler;
import emu.grasscutter.net.proto.RetcodeOuterClass.Retcode;
import emu.grasscutter.net.proto.SalesmanTakeSpecialRewardReqOuterClass.SalesmanTakeSpecialRewardReq;
import emu.grasscutter.server.game.GameSession;
import emu.grasscutter.server.packet.send.PacketSalesmanTakeSpecialRewardRsp;

import java.util.Optional;

@Opcodes(PacketOpcodes.SalesmanTakeSpecialRewardReq)
public class HandlerSalesmanTakeSpecialRewardReq extends PacketHandler {
	
	@Override
	public void handle(GameSession session, byte[] header, byte[] payload) throws Exception {
		SalesmanTakeSpecialRewardReq req = SalesmanTakeSpecialRewardReq.parseFrom(payload);

		ActivitySalesmanData scheduleData = GameData.getActivitySalesmanDataMap().get(req.getScheduleId());

		Optional<PlayerActivityData> playerData = session.getPlayer().getActivityManager()
			.getPlayerActivityDataByActivityType(ActivityType.NEW_ACTIVITY_SALESMAN_MP);

		if (scheduleData == null || !playerData.isPresent()) {
			session.getPlayer().sendPacket(new PacketSalesmanTakeSpecialRewardRsp(req.getScheduleId(), Retcode.RET_FAIL_VALUE));
			return;
		}

		SalesmanMpActivityHandler handler = (SalesmanMpActivityHandler) playerData.get().getActivityHandler();

		session.getPlayer().sendPacket(new PacketSalesmanTakeSpecialRewardRsp(
			req.getScheduleId(), 
			handler.getSpecialReward(session.getPlayer(), playerData.get(), req.getScheduleId())));
	}

}
