package emu.grasscutter.server.event.player;

import emu.grasscutter.game.player.Player;
import emu.grasscutter.game.world.World;
import emu.grasscutter.game.world.data.TeleportProperties;
import emu.grasscutter.server.event.Cancellable;
import emu.grasscutter.server.event.types.PlayerEvent;
import emu.grasscutter.utils.Position;

public final class PlayerTeleportEvent extends PlayerEvent implements Cancellable {
    private final Position from;
    TeleportProperties teleportProperties;

    public PlayerTeleportEvent(Player player, TeleportProperties teleportProperties, Position from) {
        super(player);

        this.teleportProperties = teleportProperties;
        this.from = from;
    }

    public TeleportType getTeleportType() {
        return this.teleportProperties.getTeleportType();
    }

    public Position getSource() {
        return this.from;
    }

    public Position getDestination() {
        return this.teleportProperties.getTeleportTo();
    }

    public void setDestination(Position to) {
        this.teleportProperties.setTeleportTo(to);
    }

    public enum TeleportType {
        /**
         * There is no specified reason to teleport.
         */
        INTERNAL,

        /**
         * The player has asked to teleport to a waypoint.
         */
        WAYPOINT,

        /**
         * The player has asked to teleport using the map.
         */
        MAP,

        /**
         * The player has asked to teleport into a dungeon.
         */
        DUNGEON,

        /**
         * The player has asked to teleport using the command.
         */
        COMMAND,

        /**
         * A script has asked the teleport.
         */
        SCRIPT,

        /**
         * Client requested transmit.
         */
        CLIENT
    }
}
