package emu.grasscutter.command.commands;

import emu.grasscutter.command.Command;
import emu.grasscutter.command.CommandHandler;
import emu.grasscutter.data.GameData;
import emu.grasscutter.data.excels.ActivitySalesmanData;
import emu.grasscutter.game.activity.PlayerActivityData;
import emu.grasscutter.game.activity.salesmanmp.SalesmanMpActivityHandler;
import emu.grasscutter.game.activity.salesmanmp.SalesmanMpPlayerData;
import emu.grasscutter.game.player.Player;
import emu.grasscutter.game.props.ActivityType;
import emu.grasscutter.game.props.ElementType;
import emu.grasscutter.net.proto.ActivityInfoOuterClass.ActivityInfo;
import emu.grasscutter.server.packet.send.PacketActivityInfoNotify;

import java.util.List;
import java.util.Optional;

import static emu.grasscutter.utils.Language.translate;

@Command(label = "salesmanMpActivity",
        aliases = {"smpa"},
        usage = {
            "dayReward [change<rewardId>|getId|reset|nextDay] ", 
            "specialReward [setTaken<true|false|none>|setTakeable<true|false|none>] ", 
            "schedule [change<scheduleId>|reset]"},
        permission = "player.smpa",
        permissionTargeted = "player.smpa.others")
public final class SalesmanMpActivityCommand implements CommandHandler {

    @Override
    public void execute(Player sender, Player targetPlayer, List<String> args) {
        if (args.size() == 0) {
            sendUsageMessage(sender);
            return;
        }

        Optional<PlayerActivityData> playerDataOption = targetPlayer.getActivityManager()
            .getPlayerActivityDataByActivityType(ActivityType.NEW_ACTIVITY_SALESMAN_MP);
        
        if (!playerDataOption.isPresent()) {
            CommandHandler.sendMessage(sender, translate(sender, "commands.salesmanMpActivity.player_data_not_found"));
            return;
        }

        PlayerActivityData playerData = playerDataOption.get();
        SalesmanMpActivityHandler handler = (SalesmanMpActivityHandler) playerData.getActivityHandler();
        SalesmanMpPlayerData salesmanPlayerData = handler.getSalesmanMpPlayerData(playerData);

        ActivitySalesmanData salesmanExcelData = GameData.getActivitySalesmanDataMap().get(handler.getActivityConfigItem().getScheduleId());
        if (salesmanExcelData == null) {
            CommandHandler.sendMessage(sender, translate(sender, "commands.salesmanMpActivity.resource_data_not_found"));
            return;
        }

        String param1 = args.remove(0).toLowerCase();
        switch (param1) {
            case "dayreward" -> {
                if (args.size() < 1) {
                    CommandHandler.sendMessage(sender, translate(sender, "commands.salesmanMpActivity.missing_param", 2));
                    return;
                }
                String param2 = args.remove(0).toLowerCase();
                switch (param2) {
                    case "change" -> {
                        int newReward = 0;
                        if (args.size() < 1) { // will randomly roll if no parameter
                            List<Integer> copiedReward = salesmanPlayerData.getReceivedReward().stream().toList();
                            copiedReward.add(salesmanPlayerData.getDayRewardId()); // add current reward to pool temporary so that it doesnt get into the next roll
                            newReward = salesmanPlayerData.getRandomNormalDayReward(salesmanExcelData, copiedReward);
                            if (newReward == 0) {
                                CommandHandler.sendMessage(sender, translate(sender, "commands.salesmanMpActivity.notify_reset"));
                                return;
                            }
                        } else {
                            String param = args.remove(0);
                            if (param.isBlank() || !param.chars().allMatch(Character::isDigit)) { // empty parameter or not digits
                                CommandHandler.sendMessage(sender, translate(sender, "commands.salesmanMpActivity.invalid_param", 3));
                                return;
                            }

                            newReward = Integer.parseInt(param);
                            if (salesmanPlayerData.getReceivedReward().contains(newReward)) { // if reward already taken
                                CommandHandler.sendMessage(sender, translate(sender, "commands.salesmanMpActivity.reward_taken"));
                                return;
                            }
                            if (!salesmanExcelData.getNormalRewardIdList().contains(newReward)) { // invalid reward
                                CommandHandler.sendMessage(sender, translate(sender, "commands.salesmanMpActivity.invalid_param", 3));
                                return;
                            }
                        }
                        salesmanPlayerData.resetStatus(newReward);
                    }
                    case "reset" -> {
                        salesmanPlayerData.resetStatus(salesmanPlayerData.getDayRewardId());
                    }
                    case "getid" -> {
                        CommandHandler.sendMessage(sender, translate(sender, "commands.salesmanMpActivity.get_reward_id", salesmanPlayerData.getDayRewardId()));
                        return;
                    }
                    case "nextday" -> {
                        salesmanPlayerData.resetDayReward(handler.getActivityConfigItem().getScheduleId());
                    }
                    default -> {
                        CommandHandler.sendMessage(sender, translate(sender, "commands.salesmanMpActivity.unknown_command", param2));
                        return;
                    }
                }
            }
            case "specialreward" -> {
                if (args.size() < 1) {
                    CommandHandler.sendMessage(sender, translate(sender, "commands.salesmanMpActivity.missing_param", 2));
                    return;
                }
                String param2 = args.remove(0).toLowerCase();
                switch (param2) {
                    case "settaken" -> {
                        boolean newState = false;
                        if (args.size() < 1) { // assume to toggle opposite of what already is if no param
                            newState = !salesmanPlayerData.isHasTakenSpecialReward();
                        } else {
                            newState = Boolean.parseBoolean(args.remove(0).toLowerCase());
                            if (salesmanPlayerData.isHasTakenSpecialReward() == newState) {
                                CommandHandler.sendMessage(sender, translate(sender, "commands.salesmanMpActivity.state_already_is"));
                                return;
                            }
                        }
                        salesmanPlayerData.setHasTakenSpecialReward(newState);
                    }
                    case "settakeable" -> {
                        boolean takeable = false;
                        if (args.size() < 1) { // assume to toggle opposite of what already is if no param
                            takeable = (salesmanPlayerData.getDeliverCount() >= salesmanPlayerData.getCondDayCount());
                        } else {
                            takeable = Boolean.parseBoolean(args.remove(0).toLowerCase());
                            if (takeable == (salesmanPlayerData.getDeliverCount() >= salesmanPlayerData.getCondDayCount())) {
                                CommandHandler.sendMessage(sender, translate(sender, "commands.salesmanMpActivity.state_already_is"));
                                return;
                            }
                        }
                        salesmanPlayerData.setDeliverCount(takeable ? salesmanPlayerData.getCondDayCount() : 0);
                    }
                }
            }
            case "schedule" -> {
                if (args.size() < 1) {
                    CommandHandler.sendMessage(sender, translate(sender, "commands.salesmanMpActivity.missing_param", 2));
                    return;
                }
                String param2 = args.remove(0).toLowerCase();
                switch (param2) {
                    case "change" -> {
                        if (args.size() < 1 || !args.get(0).chars().allMatch(Character::isDigit)) {
                            CommandHandler.sendMessage(sender, translate(sender, "commands.salesmanMpActivity.invalid_param", 3));
                            return;
                        }
                        int newScheduleId = Integer.parseInt(args.remove(0));
                        if (GameData.getActivitySalesmanDataMap().get(newScheduleId) == null || ((int) newScheduleId / 1000) != 5012) {
                            CommandHandler.sendMessage(sender, translate(sender, "commands.salesmanMpActivity.invalid_schedule_id", newScheduleId));
                            return;
                        }
                        salesmanPlayerData = SalesmanMpPlayerData.create(newScheduleId);
                    }
                    case "reset" -> {
                        salesmanPlayerData = SalesmanMpPlayerData.create(handler.getActivityConfigItem().getScheduleId());
                    }
                }
            }
            default -> {
                sendUsageMessage(sender);
                return;
            }
        }
        playerData.setDetail(salesmanPlayerData);
        playerData.save();
        CommandHandler.sendMessage(sender, translate(sender, "commands.salesmanMpActivity.success"));
        targetPlayer.sendPacket(new PacketActivityInfoNotify(handler.toProto(playerData, targetPlayer.getActivityManager().getConditionExecutor())));
    }
}
