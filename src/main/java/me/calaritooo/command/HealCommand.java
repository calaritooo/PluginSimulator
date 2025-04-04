package me.calaritooo.command;

import me.calaritooo.util.MessageBus;
import me.calaritooo.player.Player;

public class HealCommand implements Command {

    @Override
    public String getName() {
        return "/heal";
    }

    @Override
    public String getDescription() {
        return "Heals the specified player by the given amount.";
    }

    @Override
    public String getUsage() {
        return "Usage: /heal <player> <qty>";
    }

    @Override
    public void execute(Player player, String[] args) {
        if (args.length == 2) {
            player.heal(Integer.parseInt(args[1]));
        } else { MessageBus.send(getUsage()); }
    }
}
