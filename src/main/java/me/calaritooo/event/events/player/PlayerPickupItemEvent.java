package me.calaritooo.event.events.player;

import me.calaritooo.event.Cancelable;
import me.calaritooo.player.Player;

public class PlayerPickupItemEvent implements Cancelable {

    // AWAITING ITEM CLASS IMPLEMENTATION

    private final Player player;
    private boolean canceled = false;

    public PlayerPickupItemEvent(Player player) {
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
