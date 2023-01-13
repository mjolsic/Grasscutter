package emu.grasscutter.game.activity.bartender;

import emu.grasscutter.data.GameData;
import emu.grasscutter.net.proto.BartenderActivityDetailInfoOuterClass.BartenderActivityDetailInfo;
import emu.grasscutter.net.proto.BartenderLevelInfoOuterClass.BartenderLevelInfo;
import emu.grasscutter.net.proto.BartenderTaskInfoOuterClass.BartenderTaskInfo;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Set;
import java.util.Map;
import java.util.stream.Collectors;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder(builderMethodName = "of")
public class BartenderPlayerData {
    boolean isContentClosed;
    Map<Integer, LevelInfoItem> unlockLevel;
    Set<Integer> unlockItemList;
    Set<Integer> unlockFormulaList;
    Map<Integer, TaskInfoItem> unlockTask;
    Set<Integer> finishedOrder;
    boolean isDevelopModuleOpen;

    public static BartenderPlayerData create() {
        Set<Integer> unlockedMaterials = GameData.getBartenderMaterialUnlockDataMap().values().stream()
            .filter(x -> x.getUnlockDayCount() == 1)
            .map(x-> x.getId()).collect(Collectors.toSet());

        return BartenderPlayerData.of()
            .isContentClosed(false)
            .unlockLevel(Map.of())
            .unlockItemList(unlockedMaterials)
            .unlockFormulaList(GameData.getBartenderFormulaDataMap().values().stream()
                .filter(x -> x.isUnlock(unlockedMaterials, 1))
                .map(x-> x.getId()).collect(Collectors.toSet()))
            .unlockTask(Map.of())
            .finishedOrder(Set.of())
            .isDevelopModuleOpen(false)
            .build();
    }

    public BartenderActivityDetailInfo toProto() {
        return BartenderActivityDetailInfo.newBuilder()
            .setIsContentClosed(isContentClosed())
            .addAllUnlockLevelList(getUnlockLevel().values().stream().map(LevelInfoItem::toProto).toList())
            .addAllUnlockItemList(getUnlockItemList())
            .addAllUnlockFormulaList(getUnlockFormulaList())
            .addAllUnlockTaskList(getUnlockTask().values().stream().map(TaskInfoItem::toProto).toList())
            .setIsDevelopModuleOpen(isDevelopModuleOpen())
            .build();
    }

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    @Builder(builderMethodName = "of")
    public static class LevelInfoItem {
        int id;
        boolean isFinish;
        int maxScore;

        public static LevelInfoItem create(int id) {
            return LevelInfoItem.of()
                .id(id)
                .isFinish(false)
                .maxScore(0)
                .build();
        }

        public BartenderLevelInfo toProto() {
            return BartenderLevelInfo.newBuilder()
                .setId(getId())
                .setIsFinish(isFinish())
                .setMaxScore(getMaxScore())
                .build();
        }
    }

    @Data
    @FieldDefaults(level = AccessLevel.PRIVATE)
    @Builder(builderMethodName = "of")
    public static class TaskInfoItem {
        int id;
        boolean isFinish;

        public static TaskInfoItem create(int id){
            return TaskInfoItem.of()
                .id(id)
                .isFinish(false)
                .build();
        }

        public BartenderTaskInfo toProto() {
            return BartenderTaskInfo.newBuilder()
                .setId(getId())
                .setIsFinish(isFinish())
                .build();
        }
    }
}


