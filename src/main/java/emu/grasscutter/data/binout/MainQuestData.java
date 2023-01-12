package emu.grasscutter.data.binout;

import dev.morphia.annotations.Entity;
import dev.morphia.annotations.Transient;
import emu.grasscutter.game.quest.enums.QuestType;
import emu.grasscutter.game.quest.enums.TalkExec;
import lombok.Data;
import java.util.List;
import java.util.Objects;

public class MainQuestData {
    private int id;
    private int ICLLDPJFIMA;
    private int series;
    private QuestType type;

    private long titleTextMapHash;
    private int[] suggestTrackMainQuestList;
    private int[] rewardIdList;

    private SubQuestData[] subQuests;
    private List<TalkData> talks;
    private long[] preloadLuaList;

    public int getId() {
        return id;
    }

    public int getSeries() {
        return series;
    }

    public QuestType getType() {
        return type;
    }

    public long getTitleTextMapHash() {
        return titleTextMapHash;
    }

    public int[] getSuggestTrackMainQuestList() {
        return suggestTrackMainQuestList;
    }

    public int[] getRewardIdList() {
        return rewardIdList;
    }

    public SubQuestData[] getSubQuests() {
        return subQuests;
    }
    public List<TalkData> getTalks() {
        return talks;
    }

    public void onLoad() {
        this.talks = talks.stream().filter(Objects::nonNull).toList();
    }

    @Data
    public static class SubQuestData {
        private int subId;
        private int order;
    }


    @Data @Entity
    public static class TalkData {
        private int id;
        private String heroTalk;
        @Transient private List<TalkExecParam> finishExec;

        public TalkData() {}
        public TalkData(int id, String heroTalk) {
            this.id = id;
            this.heroTalk = heroTalk;
        }

        @Data
        public static class TalkExecParam {
            TalkExec type;
            String[] param;
        }
    }
}
