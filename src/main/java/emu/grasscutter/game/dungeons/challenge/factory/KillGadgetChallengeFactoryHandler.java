package emu.grasscutter.game.dungeons.challenge.factory;

import emu.grasscutter.game.dungeons.challenge.WorldChallenge;
import emu.grasscutter.game.dungeons.challenge.enums.ChallengeType;
import emu.grasscutter.game.dungeons.challenge.trigger.InTimeTrigger;
import emu.grasscutter.game.dungeons.challenge.trigger.KillGadgetTrigger;
import emu.grasscutter.game.world.Scene;
import emu.grasscutter.scripts.data.SceneGroup;

import java.util.List;

import static emu.grasscutter.game.dungeons.challenge.enums.ChallengeType.CHALLENGE_TRIGGER_IN_TIME;

public class KillGadgetChallengeFactoryHandler implements ChallengeFactoryHandler {
    @Override
    public boolean isThisType(ChallengeType challengeType) {
        // kill gadgets(explosive barrel) in time
        // ActiveChallenge with 56,201,20,2,201,4
        // open chest in time
        // ActiveChallenge with 666,202,30,7,202,1
        return challengeType == CHALLENGE_TRIGGER_IN_TIME;
    }

    @Override
    public WorldChallenge build(int challengeIndex, int challengeId, int param3, int param4, int param5, int param6, Scene scene, SceneGroup group) {
        return new WorldChallenge(
                scene, group,
                challengeId, // Id
                challengeIndex, // Index
                List.of(param3, param6, 0),
                param3, // Limit
                param6,  // Goal
                List.of(new InTimeTrigger(), new KillGadgetTrigger())
                );
    }
}
