package me.calaritooo.command;

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

    public String execute(Player player, String[] args) {
        System.exit(0);
        return "Exiting the game...";
    }
}
