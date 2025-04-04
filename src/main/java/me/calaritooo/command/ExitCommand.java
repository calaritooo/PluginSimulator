package me.calaritooo.command;

import me.calaritooo.util.MessageBus;
import me.calaritooo.player.Player;

public class ExitCommand implements Command {

    public String getName() {
        return "/exit";
    }

    public String getDescription() {
        return "Exit the engine";
    }

    public String getUsage() {
        return "Usage: /exit";
    }

    public void execute(Player player, String[] args) {
        MessageBus.send("Exiting the engine...");
        System.exit(0);
    }
}
