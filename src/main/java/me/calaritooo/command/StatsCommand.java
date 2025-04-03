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
    public String execute(Player player, String[] args) {
        // View (no args) //
        if (args.length == 1) {
            return player.getStats();

        // set //
        } else if (args.length == 4 && args[2].equals("set")) {
            try {
                String stat = args[1];
                int value = Integer.parseInt(args[3]);
                return switch (stat) {
                    case "max_health" -> {
                        player.setMaxHealth(value);
                        yield "Max health set to " + value + ".";
                    }
                    case "health" -> {
                        player.setHealth(value);
                        yield "Health set to " + value + ".";
                    }
                    default -> "Unknown stat: " + stat;
                };
            } catch (NumberFormatException e) {
                return "Invalid value: " + args[1];
            }
        } else { return getUsage(); }
    }
}
