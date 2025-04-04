package me.calaritooo.event.events.player;

import me.calaritooo.event.Cancelable;
import me.calaritooo.player.Player;

public class PlayerMoveEvent implements Cancelable {

    private final Player player;
    private boolean canceled = false;

    // AWAITING MOVE CLASS IMPLEMENTATION

    public PlayerMoveEvent(Player player) {
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
