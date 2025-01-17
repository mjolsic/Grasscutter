package emu.grasscutter.scripts.lua_engine;

import emu.grasscutter.Loggers;
import emu.grasscutter.game.entity.EntityGadget;
import emu.grasscutter.scripts.SceneScriptManager;
import emu.grasscutter.scripts.constants.IntValueEnum;
import emu.grasscutter.scripts.data.SceneGroup;
import emu.grasscutter.scripts.data.ScriptArgs;
import lombok.Getter;
import org.slf4j.Logger;

import javax.annotation.Nullable;
import javax.script.ScriptEngine;

public interface LuaEngine {
    Logger logger = Loggers.getScriptSystem();

    <T extends Enum<T>> boolean addGlobalEnumByOrdinal(String name, T[] enumArray);

    <T extends Enum<T> & IntValueEnum> boolean addGlobalEnumByIntValue(String name, T[] enumArray);

    boolean addGlobalStaticClass(String name, Class<?> staticClass);

    boolean addObject(String name, Object object);

    Serializer getSerializer();

    @Nullable
    LuaScript getScript(String scriptName, ScriptType scriptType);

    LuaTable getTable(Object table);

    LuaTable createTable();

}
