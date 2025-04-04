package me.calaritooo.command;

import me.calaritooo.gui.IOProvider;
import me.calaritooo.player.Player;

public class ExitCommand implements Command {

    public String getName() {
        return "/exit";
    }

    public String getDescription() {
        return "Exit the game";
    }

    public String getUsage() {
        return "Usage: /exit";
    }

    public void execute(Player player, String[] args) {
        IOProvider.send("Exiting the game...");
        System.exit(0);
    }
}
