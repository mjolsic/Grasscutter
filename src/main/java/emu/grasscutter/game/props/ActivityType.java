package emu.grasscutter.game.props;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@Getter
@AllArgsConstructor
public enum ActivityType {
    NONE(0),
    NEW_ACTIVITY_TRIAL_AVATAR(4),
    NEW_ACTIVITY_PERSONAL_LIINE(8),
    NEW_ACTIVITY_SALESMAN_MP(1205),
    NEW_ACTIVITY_GENERAL_BANNER(2100),
    NEW_ACTIVITY_MUSIC_GAME(2202),
    NEW_ACTIVITY_BARTENDER(2502),
    NEW_ACTIVITY_PHOTO(2603),
    NEW_ACTIVITY_FUNGUS_FIGHTER(3201),
    NEW_ACTIVITY_EFFIGY_CHALLENGE_V2(3203),
    NEW_ACTIVITY_COIN_COLLECT(3301)
    ;

    private final int value;
    private static final Int2ObjectMap<ActivityType> map = new Int2ObjectOpenHashMap<>();
    private static final Map<String, ActivityType> stringMap = new HashMap<>();

    static {
        Stream.of(values()).forEach(e -> {
            map.put(e.getValue(), e);
            stringMap.put(e.name(), e);
        });
    }

    public static ActivityType getTypeByValue(int value) {
        return map.getOrDefault(value, NONE);
    }

    public static ActivityType getTypeByName(String name) {
        return stringMap.getOrDefault(name, NONE);
    }
}
