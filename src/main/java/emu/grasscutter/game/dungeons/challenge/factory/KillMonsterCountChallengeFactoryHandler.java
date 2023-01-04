package emu.grasscutter.game.dungeons.challenge.factory;

import emu.grasscutter.game.dungeons.challenge.WorldChallenge;
import emu.grasscutter.game.dungeons.challenge.enums.ChallengeType;
import emu.grasscutter.game.dungeons.challenge.trigger.InTimeTrigger;
import emu.grasscutter.game.dungeons.challenge.trigger.KillMonsterTrigger;
import emu.grasscutter.game.world.Scene;
import emu.grasscutter.scripts.data.SceneGroup;

import java.util.List;

public class KillMonsterCountChallengeFactoryHandler implements ChallengeFactoryHandler{
    @Override
    public boolean isThisType(ChallengeType challengeType) {
        // ActiveChallenge with 180,180,45,133108061,1,0
        return challengeType == ChallengeType.CHALLENGE_KILL_COUNT;
    }

    @Override
    public WorldChallenge build(int challengeIndex, int challengeId, int param3, int param4, int param5, int param6, Scene scene, SceneGroup group) {
        var realGroup = scene.getScriptManager().getGroupById(param4);
        return new WorldChallenge(
                scene, realGroup,
                challengeId, // Id
                challengeIndex, // Index
                List.of(param5, param3),
                param3, // Limit
                param5,  // Goal
                List.of(new KillMonsterTrigger())
                );
    }
}
