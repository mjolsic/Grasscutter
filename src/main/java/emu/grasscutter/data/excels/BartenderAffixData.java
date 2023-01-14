package emu.grasscutter.data.excels;

import com.google.gson.annotations.SerializedName;
import emu.grasscutter.data.GameResource;
import emu.grasscutter.data.ResourceType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

import java.util.List;

@ResourceType(name = "BartenderAffixExcelConfigData.json")
@EqualsAndHashCode(callSuper=false)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BartenderAffixData extends GameResource {
    int id;
    @SerializedName(value="condition", alternate={"EMPODCNGOFA"})
    String condition;
    int materialId;
    @SerializedName(value="materialCount", alternate={"GHKMMNOPHLF"})
    int materialCount;

    @Override
    public int getId() {
        return this.id;
    }
}
