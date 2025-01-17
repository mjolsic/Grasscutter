package emu.grasscutter.scripts.data;

import emu.grasscutter.game.props.EntityIdType;
import emu.grasscutter.scripts.constants.ScriptRegionShape;
import emu.grasscutter.utils.Position;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
public class SceneRegion extends SceneObject{
    private int shape;
    // for CUBIC
    private Position size;
    // for SPHERE AND CYLINDER
    private int radius;
    // for CYLINDER and POLYGON
    private float height;
    private List<Position> point_array;
    private List<String> team_ability_group;
    private boolean is_trigger_reload_group;

    public boolean contains(Position position) {
        switch (shape) {
            case ScriptRegionShape.CUBIC:
                return (Math.abs(pos.getX() - position.getX()) <= size.getX()/2f) &&
                       (Math.abs(pos.getY() - position.getY()) <= size.getY()/2f) &&
                       (Math.abs(pos.getZ() - position.getZ()) <= size.getZ()/2f);
            case ScriptRegionShape.SPHERE:
                var x = Math.pow(pos.getX() - position.getX(), 2);
                var y = Math.pow(pos.getY() - position.getY(), 2);
                var z = Math.pow(pos.getZ() - position.getZ(), 2);
                // ^ means XOR in java!
                return x + y + z <= (radius*radius);
        }
        return false;
    }

    public int getGroupId() {
        return group.getId();
    }

    @Override
    public EntityIdType getType() {
        return EntityIdType.REGION;
    }
}
