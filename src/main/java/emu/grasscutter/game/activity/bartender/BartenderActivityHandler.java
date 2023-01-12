package emu.grasscutter.game.activity.bartender;

import emu.grasscutter.game.activity.ActivityHandler;
import emu.grasscutter.game.activity.GameActivity;
import emu.grasscutter.game.activity.PlayerActivityData;
import emu.grasscutter.game.player.Player;
import emu.grasscutter.game.props.ActivityType;
import emu.grasscutter.net.proto.ActivityInfoOuterClass;
import emu.grasscutter.utils.JsonUtils;

import static emu.grasscutter.game.quest.enums.QuestCond.QUEST_COND_ACTIVITY_COND;

import java.util.Set;

@GameActivity(ActivityType.NEW_ACTIVITY_BARTENDER)
public class BartenderActivityHandler extends ActivityHandler {

    @Override
    public void onInitPlayerActivityData(PlayerActivityData playerActivityData) {
        BartenderPlayerData bartenderPlayerData = BartenderPlayerData.create();

        playerActivityData.setDetail(bartenderPlayerData);
    }

    @Override
    public void onProtoBuild(PlayerActivityData playerActivityData, ActivityInfoOuterClass.ActivityInfo.Builder activityInfo) {
        BartenderPlayerData bartenderPlayerData = getBartenderPlayerData(playerActivityData);
        activityInfo.setBartenderInfo(bartenderPlayerData.toProto());
    }

    @Override
    public void addActivityQuest(Player player) {
        // v2.5 bartender activity quests conditions
        Set<Integer> activityQuestCond = Set.of(5062001, 5062002, 5062003, 5062004, 5062005, 5062006, 5062007, 5062008, 5062009);

        // activityQuestCond.forEach(c -> player.getQuestManager().queueEvent(QUEST_COND_ACTIVITY_COND, c, 1));
    }

    public BartenderPlayerData getBartenderPlayerData(PlayerActivityData playerActivityData) {
        if (playerActivityData.getDetail() == null || playerActivityData.getDetail().isBlank()) {
            onInitPlayerActivityData(playerActivityData);
            playerActivityData.save();
        }

        return JsonUtils.decode(playerActivityData.getDetail(), BartenderPlayerData.class);
    }
}
