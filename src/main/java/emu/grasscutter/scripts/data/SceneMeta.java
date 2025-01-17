package emu.grasscutter.scripts.data;

import com.github.davidmoten.rtreemulti.RTree;
import com.github.davidmoten.rtreemulti.geometry.Geometry;
import emu.grasscutter.Grasscutter;
import emu.grasscutter.scripts.SceneIndexManager;
import emu.grasscutter.scripts.ScriptLoader;
import emu.grasscutter.scripts.lua_engine.LuaScript;
import emu.grasscutter.scripts.lua_engine.ScriptType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.val;

import javax.script.Bindings;
import javax.script.CompiledScript;
import javax.script.ScriptException;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ToString
@Getter
public class SceneMeta {

    private SceneConfig config;
    private Map<Integer, SceneBlock> blocks;

    private LuaScript context;

    private RTree<SceneBlock, Geometry> sceneBlockIndex;

    public static SceneMeta of(int sceneId) {
        return new SceneMeta().load(sceneId);
    }

    public SceneMeta load(int sceneId) {
        // Get compiled script if cached
        val cs = ScriptLoader.getScript("Scene/" + sceneId + "/scene" + sceneId + ".lua", ScriptType.DATA_STORAGE);

        if (cs == null) {
            Grasscutter.getLogger().warn("No script found for scene " + sceneId);
            return null;
        }

        // Eval script
        try {
            cs.evaluate();

            this.config = cs.getGlobalVariable("scene_config", SceneConfig.class);

            // TODO optimize later
            // Create blocks
            List<Integer> blockIds = cs.getGlobalVariableList("blocks", Integer.class);
            List<SceneBlock> blocks = cs.getGlobalVariableList("block_rects", SceneBlock.class);

            for (int i = 0; i < blocks.size(); i++) {
                SceneBlock block = blocks.get(i);
                block.setId(blockIds.get(i));
            }

            this.blocks = blocks.stream().collect(Collectors.toMap(b -> b.getId(), b -> b, (a, b) -> a));
            this.sceneBlockIndex = SceneIndexManager.buildIndex(2, blocks, SceneBlock::toRectangle);

        } catch (ScriptException exception) {
            Grasscutter.getLogger().error("An error occurred while running a script.", exception);
            return null;
        }
        Grasscutter.getLogger().debug("Successfully loaded metadata in scene {}.", sceneId);
        return this;
    }
}
