package emu.grasscutter.data.excels;

import emu.grasscutter.data.GameData;
import emu.grasscutter.data.GameResource;
import emu.grasscutter.data.ResourceType;
import emu.grasscutter.data.common.ItemParamData;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Objects;

@ResourceType(name = "ActivitySalesmanDailyExcelConfigData.json")
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ActivitySalesmanDailyData extends GameResource {
    int dailyConfigId;
    List<ItemParamData> costItemList;
    String tracePosition;

    @Override
    public int getId() {
        return this.dailyConfigId;
    }
}
