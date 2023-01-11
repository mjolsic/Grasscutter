package emu.grasscutter.game.activity.salesmanmp;

import emu.grasscutter.data.GameData;
import emu.grasscutter.data.excels.ActivitySalesmanData;
import emu.grasscutter.net.proto.SalesmanActivityDetailInfoOuterClass.SalesmanActivityDetailInfo;
import emu.grasscutter.net.proto.SalesmanStatusTypeOuterClass.SalesmanStatusType;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.Collections;
import java.util.HashSet;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder(builderMethodName = "of")
public class SalesmanMpPlayerData {
    int specialRewardPreviewId;
    int status;
    int condDayCount;
    int dayRewardId;
    int deliverCount;
    int dayIndex;
    boolean isTodayHasDelivered;
    boolean hasTakenSpecialReward;
    Set<Integer> receivedReward;

    public static SalesmanMpPlayerData create(int scheduleId) {
        ActivitySalesmanData data = GameData.getActivitySalesmanDataMap().get(scheduleId);
        return SalesmanMpPlayerData.of()
            .specialRewardPreviewId(data.getSpecialReward().getPreviewId())
            .status(SalesmanStatusType.SALESMAN_STATUS_TYPE_UNSTARTED_VALUE)
            .condDayCount(Integer.parseInt(data.getSpecialReward().getObtainParam()))
            .dayRewardId(data.getNormalRewardIdList().get(
                data.getSpecialProbList().indexOf(Collections.max(data.getSpecialProbList()))))
            .deliverCount(0)
            .dayIndex(1)
            .isTodayHasDelivered(false)
            .hasTakenSpecialReward(false)
            .receivedReward(new HashSet<Integer>())
            .build();
    }

    public boolean start() {
        if (getStatus() <  SalesmanStatusType.SALESMAN_STATUS_TYPE_STARTED_VALUE) {
            setStatus(SalesmanStatusType.SALESMAN_STATUS_TYPE_STARTED_VALUE);
            return true;
        }
        return false;
    }

    public void deliver() {
        setStatus(SalesmanStatusType.SALESMAN_STATUS_TYPE_DELIVERED_VALUE);
        setDeliverCount(getDeliverCount()+1);
        setTodayHasDelivered(true);
        getReceivedReward().add(getDayRewardId());
    }

    public void getSpecialReward() {
        setHasTakenSpecialReward(true);
    }

    public SalesmanActivityDetailInfo toProto() {
        return SalesmanActivityDetailInfo.newBuilder()
            .setSpecialRewardPreviewId(getSpecialRewardPreviewId())
            .setStatusValue(getStatus())
            .setCondDayCount(getCondDayCount())
            .setDayRewardId(getDayRewardId())
            .setDeliverCount(getDeliverCount())
            .setDayIndex(getDayIndex())
            .setIsTodayHasDelivered(isTodayHasDelivered())
            .setIsHasTakenSpecialReward(isHasTakenSpecialReward())
            .build();
    }
}


