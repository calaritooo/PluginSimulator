package me.calaritooo.event.events.player;

import me.calaritooo.command.Command;
import me.calaritooo.event.Cancelable;
import me.calaritooo.player.Player;

public class PlayerCommandEvent implements Cancelable {

    private final Player player;
    private final Command command;
    private boolean canceled = false;

    public PlayerCommandEvent(Player player, Command command) {
        this.player = player;
        this.command = command;
    }

    public Player getPlayer() { return player; }
    public Command getCommand() { return command; }

    @Override
    public boolean isCanceled() {
        return canceled;
    }
    @Override
    public void setCancelled(boolean canceled) {
        this.canceled = canceled;
    }
}
