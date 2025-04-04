package me.calaritooo.command;

import me.calaritooo.player.Player;

public interface Command {

    String getName();
    String getDescription();
    String getUsage();
    void execute(Player player, String[] args);
}
