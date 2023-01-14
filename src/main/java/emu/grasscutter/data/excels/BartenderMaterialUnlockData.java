package emu.grasscutter.data.excels;

import com.google.gson.annotations.SerializedName;
import emu.grasscutter.data.GameResource;
import emu.grasscutter.data.ResourceType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

import java.util.List;

@ResourceType(name = "BartenderMaterialUnlockConfigData.json")
@EqualsAndHashCode(callSuper=false)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BartenderMaterialUnlockData extends GameResource {
    int id;
    @SerializedName(value="unlockDayCount", alternate={"JMLNHKCOEBA"})
    int unlockDayCount;

    @Override
    public int getId() {
        return this.id;
    }
}
