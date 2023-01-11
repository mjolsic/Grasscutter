package emu.grasscutter.game.activity.salesmanmp;

import emu.grasscutter.data.GameData;
import emu.grasscutter.data.excels.ActivitySalesmanData;
import emu.grasscutter.net.proto.SalesmanActivityDetailInfoOuterClass.SalesmanActivityDetailInfo;
import emu.grasscutter.net.proto.SalesmanStatusTypeOuterClass.SalesmanStatusType;
import emu.grasscutter.utils.Utils;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Set;
import java.util.Collection;
import java.util.List;
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
    int lastRefreshTime;

    public static SalesmanMpPlayerData create(int scheduleId) {
        ActivitySalesmanData data = GameData.getActivitySalesmanDataMap().get(scheduleId);
        return SalesmanMpPlayerData.of()
            .specialRewardPreviewId(data.getSpecialReward().getPreviewId())
            .status(SalesmanStatusType.SALESMAN_STATUS_TYPE_UNSTARTED_VALUE)
            .condDayCount(Integer.parseInt(data.getSpecialReward().getObtainParam()))
            .dayRewardId(getRandomNormalDayReward(data, List.of()))
            .deliverCount(0)
            .lastRefreshTime(Utils.getCurrentSeconds())
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

    public static int getRandomNormalDayReward(ActivitySalesmanData data, Collection<Integer> receivedReward) {
        List<Integer> remainingReward = data.getNormalRewardIdList().stream()
            .filter(reward -> !receivedReward.contains(reward)).toList();

        // return 0 if all the normal day reward is taken
        return remainingReward.isEmpty() ? 0 : Utils.drawRandomListElement(remainingReward);
    }

    public void resetDayReward(int scheduleId) {
        ActivitySalesmanData data = GameData.getActivitySalesmanDataMap().get(scheduleId);
        if (data == null) return;

        int newReward = getRandomNormalDayReward(data, getReceivedReward());
        setDayRewardId(newReward);
        setTodayHasDelivered(newReward == 0 ? true : false); // if no more reward to take
        setDayIndex(getDayIndex()+1);
        setStatus(SalesmanStatusType.SALESMAN_STATUS_TYPE_UNSTARTED_VALUE);
        setLastRefreshTime(Utils.getCurrentSeconds());
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


