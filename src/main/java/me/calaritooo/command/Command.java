package me.calaritooo.command;

import me.calaritooo.player.Player;

public interface Command {

    String getName();
    String getDescription();
    String getUsage();
    String execute(Player player, String[] args);
}
