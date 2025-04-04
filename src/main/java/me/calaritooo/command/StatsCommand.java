package me.calaritooo.command;

import me.calaritooo.player.Player;

public class StatsCommand implements Command {
    
    @Override
    public String getName() {
        return "/stats";
    }
    
    @Override
    public String getDescription() {
        return "Use this to view or change player stats.";
    }
    
    @Override
    public String getUsage() {
        return "Usage: /stats <health/max_health> set <value>";
    }
    
    @Override
    public void execute(Player player, String[] args) {
        // View (no args) //
        if (args.length == 1) {
            player.getStats();

        // set //
        } else if (args.length == 4 && args[2].equals("set")) {
            try {
                String stat = args[1];
                int value = Integer.parseInt(args[3]);
                switch (stat) {
                    case "max_health" -> {
                        player.setMaxHealth(value);
                    }
                    case "health" -> {
                        player.setHealth(value);
                    }
                    default -> player.send("Unknown stat: " + stat);
                };
            } catch (NumberFormatException e) {
                player.send("Invalid value: " + args[1]);
            }
        } else { player.send(getUsage()); }
    }
}
