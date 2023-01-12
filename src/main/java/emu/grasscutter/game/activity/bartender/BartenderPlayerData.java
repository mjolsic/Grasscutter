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
import java.util.HashSet;
import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder(builderMethodName = "of")
public class BartenderPlayerData {
    boolean isContentClosed;
    List<LevelInfoItem> unlockLevelList;
    Set<Integer> unlockItemList;
    Set<Integer> unlockFormulaList;
    List<TaskInfoItem> unlockTaskList;
    boolean isDevelopModuleOpen;

    public static BartenderPlayerData create() {
        return BartenderPlayerData.of()
            .isContentClosed(false)
            .unlockLevelList(List.of())
            .unlockItemList(new HashSet<>())
            .unlockFormulaList(new HashSet<>())
            .unlockTaskList(List.of())
            .isDevelopModuleOpen(false)
            .build();
    }

    public BartenderActivityDetailInfo toProto() {
        return BartenderActivityDetailInfo.newBuilder()
            .setIsContentClosed(isContentClosed())
            .addAllUnlockLevelList(getUnlockLevelList().stream().map(LevelInfoItem::toProto).toList())
            .addAllUnlockItemList(getUnlockItemList())
            .addAllUnlockFormulaList(getUnlockFormulaList())
            .addAllUnlockTaskList(getUnlockTaskList().stream().map(TaskInfoItem::toProto).toList())
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


