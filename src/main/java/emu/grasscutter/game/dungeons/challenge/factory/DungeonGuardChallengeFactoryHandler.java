package emu.grasscutter.game.dungeons.challenge.factory;

import emu.grasscutter.game.dungeons.challenge.WorldChallenge;
import emu.grasscutter.game.dungeons.challenge.enums.ChallengeType;
import emu.grasscutter.game.dungeons.challenge.trigger.GuardTrigger;
import emu.grasscutter.game.world.Scene;
import emu.grasscutter.scripts.data.SceneGroup;

import java.util.List;

import static emu.grasscutter.game.dungeons.challenge.enums.ChallengeType.CHALLENGE_KILL_COUNT_GUARD_HP;

public class DungeonGuardChallengeFactoryHandler implements ChallengeFactoryHandler{
    @Override
    public boolean isThisType(ChallengeType challengeType) {
        // ActiveChallenge with 1,188,234101003,12,3030,0
        return challengeType == CHALLENGE_KILL_COUNT_GUARD_HP;
    }

    @Override
    public WorldChallenge build(int challengeIndex, int challengeId, int param3, int param4, int param5, int param6, Scene scene, SceneGroup group) {
        var realGroup = scene.getScriptManager().getGroupById(param3);
        return new WorldChallenge(
                scene, realGroup,
                challengeId, // Id
                challengeIndex, // Index
                List.of(param4, 0),
                0, // Limit
                param5,  // Goal
                List.of(new GuardTrigger()));
    }
}
