package me.calaritooo.event.events.player;

import me.calaritooo.player.Player;

public class PlayerDoDamageEvent {

    private final Player player;
    private final int damage;

    public PlayerDoDamageEvent(Player player, int damage) {
        this.player = player;
        this.damage = damage;
    }

    public Player getPlayer() { return player; }
    public int getDamage() { return damage; }
}
