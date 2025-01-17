package emu.grasscutter.game.entity;

import emu.grasscutter.game.props.EntityIdType;
import emu.grasscutter.game.world.Scene;
import emu.grasscutter.net.proto.*;
import emu.grasscutter.scripts.data.SceneNPC;
import emu.grasscutter.utils.Position;
import it.unimi.dsi.fastutil.ints.Int2FloatMap;
import lombok.Getter;

public class EntityNPC extends GameEntity {
    @Getter(onMethod = @__(@Override))
    private final Position position;
    @Getter(onMethod = @__(@Override))
    private final Position rotation;
    private final SceneNPC metaNpc;
    @Getter private final int suiteId;

    public EntityNPC(Scene scene, SceneNPC metaNPC, int blockId, int suiteId) {
        super(scene);
        this.id = getScene().getWorld().getNextEntityId(EntityIdType.NPC);
        setConfigId(metaNPC.getConfig_id());
        setGroupId(metaNPC.getGroup().getId());
        setBlockId(blockId);
        this.suiteId = suiteId;
        this.position = metaNPC.getPos().clone();
        this.rotation = metaNPC.getRot().clone();
        this.metaNpc = metaNPC;

    }

    @Override
    public int getEntityTypeId() {
        return metaNpc.getNpc_id();
    }

    @Override
    public Int2FloatMap getFightProperties() {
        return null;
    }

    @Override
    public SceneEntityInfoOuterClass.SceneEntityInfo toProto() {

        EntityAuthorityInfoOuterClass.EntityAuthorityInfo authority = EntityAuthorityInfoOuterClass.EntityAuthorityInfo.newBuilder()
            .setAbilityInfo(AbilitySyncStateInfoOuterClass.AbilitySyncStateInfo.newBuilder())
            .setRendererChangedInfo(EntityRendererChangedInfoOuterClass.EntityRendererChangedInfo.newBuilder())
            .setAiInfo(SceneEntityAiInfoOuterClass.SceneEntityAiInfo.newBuilder()
                .setIsAiOpen(true)
                .setBornPos(getPosition().toProto()))
            .setBornPos(getPosition().toProto())
            .build();

        SceneEntityInfoOuterClass.SceneEntityInfo.Builder entityInfo = SceneEntityInfoOuterClass.SceneEntityInfo.newBuilder()
            .setEntityId(getId())
            .setEntityType(ProtEntityTypeOuterClass.ProtEntityType.PROT_ENTITY_TYPE_NPC)
            .setMotionInfo(MotionInfoOuterClass.MotionInfo.newBuilder()
                .setPos(getPosition().toProto())
                .setRot(getRotation().toProto())
                .setSpeed(VectorOuterClass.Vector.newBuilder()))
            .addAnimatorParaList(AnimatorParameterValueInfoPairOuterClass.AnimatorParameterValueInfoPair.newBuilder())
            .setEntityClientData(EntityClientDataOuterClass.EntityClientData.newBuilder())
            .setEntityAuthorityInfo(authority)
            .setLifeState(1);


        entityInfo.setNpc(SceneNpcInfoOuterClass.SceneNpcInfo.newBuilder()
            .setNpcId(metaNpc.getNpc_id())
            .setBlockId(getBlockId())
            .build());

        return entityInfo.build();
    }
}
