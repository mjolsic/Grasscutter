package emu.grasscutter.data.excels;

import com.google.gson.annotations.SerializedName;
import emu.grasscutter.data.common.ItemParamData;
import emu.grasscutter.data.GameResource;
import emu.grasscutter.data.ResourceType;
import emu.grasscutter.game.activity.bartender.BartenderCupSize;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ResourceType(name = "BartenderOrderExcelConfigData.json")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BartenderOrderData extends GameResource {
    int id;
    @SerializedName(value="formulaId", alternate={"HEJHENIKGIN"})
    int formulaId;
    @SerializedName(value="affixes", alternate={"KKEKLJMGNDC"})
    List<Integer> affixes;
    @SerializedName(value="cupSize", alternate={"NHJHPDNJJLM"})
    String cupSize;
    BartenderCupSize bartenderCupSize;

    @Override
    public int getId() {
        return this.id;
    }
    @Override
    public void onLoad() {
        this.affixes = this.affixes.stream().filter(x -> x > 0).toList();
        this.bartenderCupSize = BartenderCupSize.getTypeByName(this.cupSize);
        
    }
}
