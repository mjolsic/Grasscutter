package emu.grasscutter.game.entity.platform;

import emu.grasscutter.Grasscutter;
import emu.grasscutter.data.binout.ConfigGadget;
import emu.grasscutter.game.entity.*;
import emu.grasscutter.game.entity.gadget.GadgetAbility;
import emu.grasscutter.game.entity.gadget.platform.AbilityRoute;
import emu.grasscutter.game.player.Player;
import emu.grasscutter.game.props.PlayerProperty;
import emu.grasscutter.game.world.Scene;
import emu.grasscutter.net.proto.*;
import emu.grasscutter.server.packet.send.PacketSceneTimeNotify;
import emu.grasscutter.utils.Position;
import emu.grasscutter.utils.ProtoHelper;
import lombok.Getter;

public class EntitySolarIsotomaElevatorPlatform extends EntityGadget {
    public EntitySolarIsotomaElevatorPlatform(EntitySolarIsotomaClientGadget isotoma, Scene scene, int gadgetId, Position pos, Position rot) {
        super(scene, gadgetId, pos, rot);
        setOwner(isotoma);
        this.setRouteConfig(new AbilityRoute(rot, false, false, pos));
        this.setContent(new GadgetAbility(this, isotoma));
    }

    @Override
    protected void fillFightProps(ConfigGadget configGadget) {
        if (configGadget == null || configGadget.getCombat() == null) {
            return;
        }
        var combatData = configGadget.getCombat();
        var combatProperties = combatData.getProperty();

        if (combatProperties.isUseCreatorProperty()) {
            //If useCreatorProperty == true, use owner's property;
            GameEntity ownerEntity = getOwner();
            if (ownerEntity != null) {
                getFightProperties().putAll(ownerEntity.getFightProperties());
                return;
            } else {
                Grasscutter.getLogger().warn("Why gadget owner is null?");
            }
        }

        super.fillFightProps(configGadget);
    }
}
