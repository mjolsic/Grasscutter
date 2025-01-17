package emu.grasscutter.scripts.lua_engine.jnlua;

import emu.grasscutter.scripts.constants.IntValueEnum;
import emu.grasscutter.scripts.lua_engine.LuaEngine;
import emu.grasscutter.scripts.lua_engine.LuaScript;
import emu.grasscutter.scripts.lua_engine.ScriptType;
import emu.grasscutter.scripts.lua_engine.Serializer;
import emu.grasscutter.utils.FileUtils;
import lombok.Getter;
import org.jetbrains.annotations.Nullable;
import org.terasology.jnlua.JavaFunction;

import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.script.SimpleBindings;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class JNLuaEngine implements LuaEngine {
    @Getter
    private final ScriptEngineManager manager;
    @Getter(onMethod = @__(@Override))
    private final Serializer serializer;
    @Getter
    private final SimpleBindings bindings;

    public JNLuaEngine() {
        manager = new ScriptEngineManager();
        bindings = new SimpleBindings();
        this.serializer = new JNLuaSerializer();

        bindings.put("print", (JavaFunction) luaState -> {
            logger.debug("[LUA] print {} ", luaState.checkString(1));
            return 1;
        });

    }

    @Override
    public <T extends Enum<T>> boolean addGlobalEnumByOrdinal(String name, T[] enumArray) {
        Map<String, Integer> table = new HashMap<>();
        Arrays.stream(enumArray).forEach(e -> {
            table.put(e.name(), e.ordinal());
            table.put(e.name().toUpperCase(), e.ordinal());
        });
        bindings.put(name, table);
        return true;
    }

    @Override
    public <T extends Enum<T> & IntValueEnum> boolean addGlobalEnumByIntValue(String name, T[] enumArray) {
        Map<String, Integer> table = new HashMap<>();
        Arrays.stream(enumArray).forEach(e -> {
            table.put(e.name(), e.getValue());
            table.put(e.name().toUpperCase(), e.getValue());
        });
        bindings.put(name, table);
        return true;
    }

    @Override
    public boolean addGlobalStaticClass(String name, Class<?> staticClass) {
        try {
            bindings.put(name, staticClass.getConstructor().newInstance());
            return true;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            logger.error("Failed to add static class to lua engine: " + name, e);
        }
        return false;
    }

    @Override
    public boolean addObject(String name, Object object) {
        bindings.put(name, object);
        return false;
    }

    @Nullable
    @Override
    public LuaScript getScript(String scriptName, ScriptType scriptType) {
        final Path scriptPath = FileUtils.getScriptPath(scriptName);
        if (!Files.exists(scriptPath)) return null;

        try {
            return new JNLuaScript(this, scriptPath, scriptType);
        } catch (IOException | ScriptException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public emu.grasscutter.scripts.lua_engine.LuaTable getTable(Object table) {
        return new JNLuaTable((AbstractMap<?, ?>) table);
    }

    @Override
    public emu.grasscutter.scripts.lua_engine.LuaTable createTable() {
        return new JNLuaTable(new HashMap<>());
    }
}
