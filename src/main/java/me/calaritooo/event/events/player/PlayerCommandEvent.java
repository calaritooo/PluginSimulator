package me.calaritooo.event.events.player;

import me.calaritooo.command.Command;
import me.calaritooo.player.Player;

public class PlayerCommandEvent {

    public final Player player;
    public final Command command;

    public PlayerCommandEvent(Player player, Command command) {
        this.player = player;
        this.command = command;
    }

    public Player getPlayer() { return player; }
    public Command getCommand() { return command; }
}
