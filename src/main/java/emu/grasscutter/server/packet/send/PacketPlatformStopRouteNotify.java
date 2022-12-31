package emu.grasscutter.server.packet.send;

import emu.grasscutter.game.entity.EntityGadget;
import emu.grasscutter.net.packet.BasePacket;
import emu.grasscutter.net.packet.PacketOpcodes;
import emu.grasscutter.net.proto.PlatformStopRouteNotifyOuterClass.PlatformStopRouteNotify;
import lombok.val;

public class PacketPlatformStopRouteNotify extends BasePacket {
    public PacketPlatformStopRouteNotify(EntityGadget gadgetEntity) {
        super(PacketOpcodes.PlatformStopRouteNotify);

        val notify = PlatformStopRouteNotify.newBuilder()
            .setPlatform(gadgetEntity.getPlatformInfo())
            .setSceneTime(gadgetEntity.getScene().getSceneTime())
            .setEntityId(gadgetEntity.getId())
            .build();

        this.setData(notify);
    }
}
