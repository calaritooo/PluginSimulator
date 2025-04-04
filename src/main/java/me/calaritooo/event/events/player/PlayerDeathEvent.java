package me.calaritooo.event.events.player;

import me.calaritooo.event.Cancelable;
import me.calaritooo.player.Player;

public class PlayerDeathEvent implements Cancelable {

    private final Player player;
    private boolean canceled = false;

    public PlayerDeathEvent(Player player) {
        this.player = player;
    }

    public Player getPlayer() { return player; }

    @Override
    public boolean isCanceled() {
        return canceled;
    }
    @Override
    public void setCancelled(boolean canceled) {
        this.canceled = canceled;
    }
}
