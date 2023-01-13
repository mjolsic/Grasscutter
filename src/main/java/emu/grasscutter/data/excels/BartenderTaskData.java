package emu.grasscutter.data.excels;

import com.google.gson.annotations.SerializedName;
import emu.grasscutter.data.GameResource;
import emu.grasscutter.data.ResourceType;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@ResourceType(name = "BartenderTaskExcelConfigData.json")
@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BartenderTaskData extends GameResource {
    int id;
    @SerializedName(value="unlockDayCount", alternate={"JMLNHKCOEBA"})
    int unlockDayCount;
    int parentQuestId;
    @SerializedName(value="meetConditionId", alternate={"LOJHOBCPPBM"})
    int meetConditionId;
    @SerializedName(value="avatar1", alternate={"FKLEDOCGMAF"})
    int avatar1;
    @SerializedName(value="avatar2", alternate={"CAACKIIMPIB"})
    int avatar2;
    int rewardPreviewId;

    @Override
    public int getId() {
        return this.id;
    }
    @Override
    public void onLoad() {
        
    }
}
