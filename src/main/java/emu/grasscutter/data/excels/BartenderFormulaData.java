package emu.grasscutter.data.excels;

import com.google.gson.annotations.SerializedName;
import emu.grasscutter.data.common.ItemParamData;
import emu.grasscutter.data.GameResource;
import emu.grasscutter.data.ResourceType;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ResourceType(name = "BartenderFormulaExcelConfigData.json")
@EqualsAndHashCode(callSuper=false)
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BartenderFormulaData extends GameResource {
    int id;
    @SerializedName(value="foundation", alternate={"BKEEPGJBHKD"})
    List<ItemParamData> foundation;
    @SerializedName(value="flavourings", alternate={"IAKAKEBFBKB"})
    List<ItemParamData> flavourings;
    @SerializedName(value="affixes", alternate={"HMKCCOLHAKK"})
    List<Integer> affixes;
    @SerializedName(value="cupSize", alternate={"CDLHGJKFNDA"})
    String cupSize;
    @SerializedName(value="unlockDayCount", alternate={"KAOBBJIMKCA"})
    int unlockDayCount;
    // "AIGCKCLMEBC": 1,
    List<ItemParamData> allCond;

    @Override
    public int getId() {
        return this.id;
    }
    @Override
    public void onLoad() {
        this.foundation = this.foundation.stream().filter(x -> x.getId() > 0).toList();
        this.flavourings = this.flavourings.stream().filter(x -> x.getId() > 0).toList();
        this.allCond = Stream.of(this.foundation, this.flavourings)
                        .flatMap(Collection::stream)
                        .collect(Collectors.toList());
        if (this.cupSize == null) {
            this.cupSize = "LIGHT";
        }
    }

    public boolean isUnlock(Collection<Integer> unlockedMaterials, int matCount, int dayCount) {
        return this.allCond.stream().allMatch(f -> unlockedMaterials.contains(f.getId()) && f.getCount() == matCount
                && dayCount >= this.unlockDayCount);
    }
}
