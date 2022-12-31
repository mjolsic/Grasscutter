package emu.grasscutter.game.dungeons.enums;

public enum DungeonPassConditionType {
    DUNGEON_COND_NONE,
    DUNGEON_COND_KILL_GROUP_MONSTER,
    DUNGEON_COND_KILL_TYPE_MONSTER,
    DUNGEON_COND_FINISH_QUEST,
    DUNGEON_COND_IN_TIME, // Missing triggers and tracking
    DUNGEON_COND_KILL_MONSTER_COUNT, // TODO handle count
    DUNGEON_COND_KILL_MONSTER,
    DUNGEON_COND_FINISH_CHALLENGE,
    DUNGEON_COND_END_MULTISTAGE_PLAY // Missing
}
