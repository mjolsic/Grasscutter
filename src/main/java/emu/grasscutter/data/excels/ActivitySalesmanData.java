package emu.grasscutter.data.excels;

import emu.grasscutter.data.GameData;
import emu.grasscutter.data.GameResource;
import emu.grasscutter.data.ResourceType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Objects;

@ResourceType(name = "ActivitySalesmanExcelConfigData.json")
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ActivitySalesmanData extends GameResource {
    int scheduleId;
    List<Integer> dailyConfigIdList;
    List<Integer> normalRewardIdList;
    List<Integer> specialRewardIdList;
    List<Float> specialProbList;
    SpecialRewardData specialReward;

    @Override
    public int getId() {
        return this.scheduleId;
    }

    @Getter
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public static class SpecialRewardData {
        String rewardType;
        String obtainMethod;
        String obtainParam;
        int id;
        int previewId;
    }
}
