package emu.grasscutter.data.excels;

import com.google.gson.annotations.SerializedName;
import emu.grasscutter.data.GameResource;
import emu.grasscutter.data.ResourceType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@ResourceType(name = "BartenderAffixExcelConfigData.json")
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BartenderAffixData extends GameResource {
    int id;
    @SerializedName(value="condition", alternate={"EMPODCNGOFA"})
    String condition;
    int materialId;
    // "GHKMMNOPHLF": 1

    @Override
    public int getId() {
        return this.id;
    }
}
