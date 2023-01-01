package emu.grasscutter.server.packet.send;

import java.util.Map.Entry;
import java.util.ArrayList;

import emu.grasscutter.Grasscutter;
import emu.grasscutter.game.avatar.Avatar;
import emu.grasscutter.game.player.Player;
import emu.grasscutter.game.player.TeamInfo;
import emu.grasscutter.net.packet.BasePacket;
import emu.grasscutter.net.packet.PacketOpcodes;
import emu.grasscutter.net.proto.AvatarTeamOuterClass.AvatarTeam;
import emu.grasscutter.net.proto.AvatarTeamUpdateNotifyOuterClass.AvatarTeamUpdateNotify;

public class PacketAvatarTeamUpdateNotify extends BasePacket {

    public PacketAvatarTeamUpdateNotify(Player player) {
        super(PacketOpcodes.AvatarTeamUpdateNotify);

        AvatarTeamUpdateNotify.Builder proto = AvatarTeamUpdateNotify.newBuilder();
        if (player.getTeamManager().getTrialTeamGuid() != null && player.getTeamManager().getTrialTeamGuid().size() > 0){
            proto.addAllTempAvatarGuidList(player.getTeamManager().getTrialTeamGuid().values().stream().toList());
        } else{
            for (Entry<Integer, TeamInfo> entry : player.getTeamManager().getTeams().entrySet()) {
                TeamInfo teamInfo = entry.getValue();
                proto.putAvatarTeamMap(entry.getKey(), teamInfo.toProto(player));
            }
        }
        this.setData(proto);
    }

    public PacketAvatarTeamUpdateNotify() {
        // neccessary to unlock team modify if it was previously locked
        super(PacketOpcodes.AvatarTeamUpdateNotify);
        this.setData(AvatarTeamUpdateNotify.newBuilder().build());
    }
}
