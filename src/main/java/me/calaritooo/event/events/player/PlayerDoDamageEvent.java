package me.calaritooo.event.events.player;

import me.calaritooo.event.Cancelable;
import me.calaritooo.player.Player;

public class PlayerDoDamageEvent implements Cancelable {

    private final Player player;
    private final int damage;
    private boolean canceled = false;

    public PlayerDoDamageEvent(Player player, int damage) {
        this.player = player;
        this.damage = damage;
    }

    public Player getPlayer() { return player; }
    public int getDamage() { return damage; }

    @Override
    public boolean isCanceled() {
        return canceled;
    }
    @Override
    public void setCancelled(boolean canceled) {
        this.canceled = canceled;
    }
}
