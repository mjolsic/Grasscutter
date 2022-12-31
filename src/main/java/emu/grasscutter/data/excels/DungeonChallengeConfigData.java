package emu.grasscutter.data.excels;

import emu.grasscutter.data.GameResource;
import emu.grasscutter.data.ResourceType;

import emu.grasscutter.game.dungeons.challenge.enums.ChallengeType;
import lombok.Getter;

@Getter
@ResourceType(name = "DungeonChallengeConfigData.json")
public class DungeonChallengeConfigData extends GameResource {
	private int id;
    private ChallengeType challengeType;
    private boolean noSuccessHint;
    private boolean noFailHint;
    private boolean isBlockTopTimer;
    //@Getter private SubChallengeFadeOutType subChallengeFadeOutRule;
    //@Getter private double subChallengeFadeOutDelayTime;
    //@Getter private SubChallengeBannerType subChallengeBannerRule;


    public int getId() {
        return id;
    }
}
