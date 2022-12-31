package emu.grasscutter.game.dungeons.enums;

import lombok.Getter;

public enum DungeonType {
    DUNGEON_NONE(false),
    DUNGEON_PLOT(true),
    DUNGEON_FIGHT(true),
    DUNGEON_THEATRE_MECHANICUS(false),
    DUNGEON_BOSS(true),
    DUNGEON_DREAMLAND(false),
    DUNGEON_UGC(false),
    DUNGEON_POTION(false),
    DUNGEON_SUMO_COMBAT(false),
    DUNGEON_ROGUELIKE(false),
    DUNGEON_CRYSTAL_LINK(false),
    DUNGEON_TOWER(false),
    DUNGEON_DISCARDED(false),
    DUNGEON_ELEMENT_CHALLENGE(true),
    DUNGEON_EFFIGY(false),
    DUNGEON_DAILY_FIGHT(false),
    DUNGEON_FLEUR_FAIR(false),
    DUNGEON_ACTIVITY(false),
    DUNGEON_CHANNELLER_SLAB_ONE_OFF(false),
    DUNGEON_CHANNELLER_SLAB_LOOP(false),
    DUNGEON_MINI_ELDRITCH(false),
    DUNGEON_CHESS(false),
    DUNGEON_IRODORI_CHESS(false),
    DUNGEON_ROGUE_DIARY(false),
    DUNGEON_SUMMER_V2(true),
    DUNGEON_MUQADAS_POTION(false),
    DUNGEON_INSTABLE_SPRAY(false);

    @Getter private final boolean countsToBattlepass;

    DungeonType(boolean countsToBattlepass){
        this.countsToBattlepass = countsToBattlepass;
    }
}
