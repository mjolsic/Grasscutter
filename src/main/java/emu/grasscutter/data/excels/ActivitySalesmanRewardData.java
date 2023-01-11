package emu.grasscutter.data.excels;

import com.google.gson.annotations.SerializedName;
import emu.grasscutter.data.GameData;
import emu.grasscutter.data.GameResource;
import emu.grasscutter.data.ResourceType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;


import java.util.List;
import java.util.Objects;

@ResourceType(name = "ActivitySalesmanRewardMatchConfigData.json")
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ActivitySalesmanRewardData extends GameResource {
    int rewardID;
    @SerializedName(value="resourceType", alternate={"ReoureceType"})
    String resourceType;

    @Override
    public int getId() {
        return this.rewardID;
    }
}
