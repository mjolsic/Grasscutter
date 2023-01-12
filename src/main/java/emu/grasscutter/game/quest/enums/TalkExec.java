package emu.grasscutter.game.quest.enums;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public enum TalkExec{
    TALK_EXEC_NONE (0),
    TALK_EXEC_SET_GADGET_STATE (1),
    TALK_EXEC_SET_GAME_TIME (2),
    TALK_EXEC_NOTIFY_GROUP_LIA (3),
    TALK_EXEC_SET_DAILY_TASK_VAR (4),
    TALK_EXEC_INC_DAILY_TASK_VAR (5),
    TALK_EXEC_DEC_DAILY_TASK_VAR (6),
    TALK_EXEC_SET_QUEST_VAR (7),
    TALK_EXEC_INC_QUEST_VAR (8),
    TALK_EXEC_DEC_QUEST_VAR (9),
    TALK_EXEC_SET_QUEST_GLOBAL_VAR (10),
    TALK_EXEC_INC_QUEST_GLOBAL_VAR (11),
    TALK_EXEC_DEC_QUEST_GLOBAL_VAR (12),
    TALK_EXEC_TRANS_SCENE_DUMMY_POINT (13),
    TALK_EXEC_SAVE_TALK_ID (14);

	private final int value;

	TalkExec(int id) {
		this.value = id;
	}

	public int getValue() {
		return value;
	}

    private static final Int2ObjectMap<TalkExec> execMap = new Int2ObjectOpenHashMap<>();
    private static final Map<String, TalkExec> execStringMap = new HashMap<>();

    static {
        Stream.of(values())
            .filter(e -> e.name().startsWith("TALK_EXEC_"))
            .forEach(e -> {
            execMap.put(e.getValue(), e);
            execStringMap.put(e.name(), e);
        });
    }

    public static TalkExec getExecByValue(int value) {
        return execMap.getOrDefault(value, TALK_EXEC_NONE);
    }

    public static TalkExec getExecByName(String name) {
        return execStringMap.getOrDefault(name, TALK_EXEC_NONE);
    }
}
