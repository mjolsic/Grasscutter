package emu.grasscutter.game.activity.bartender;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;

import java.util.Map;
import java.util.HashMap;
import java.util.stream.Stream;

public enum BartenderCupSize {
    BARTENDER_CUP_NONE(0),
    BARTENDER_CUP_SMALL(1),
    BARTENDER_CUP_MEDIUM(2),
    BARTENDER_CUP_BIG(3);

    private final int value;
	private static final Int2ObjectMap<BartenderCupSize> map = new Int2ObjectOpenHashMap<>();
	private static final Map<String, BartenderCupSize> stringMap = new HashMap<>();
	
	static {
		Stream.of(values()).forEach(e -> {
			map.put(e.getValue(), e);
			stringMap.put(e.name(), e);
		});
	}
	
	private BartenderCupSize(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}

	public static BartenderCupSize getTypeByName(String name) {
		return stringMap.getOrDefault(name, BARTENDER_CUP_NONE);
	}
	
	public static BartenderCupSize getTypeByValue(int value) {
		return map.getOrDefault(value, BARTENDER_CUP_NONE);
	}
}