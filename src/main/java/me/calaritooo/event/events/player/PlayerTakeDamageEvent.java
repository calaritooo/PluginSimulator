package me.calaritooo.event.events.player;

import me.calaritooo.player.Player;

public class PlayerTakeDamageEvent {

    private final Player player;
    private final int damage;

    public PlayerTakeDamageEvent(Player player, int damage) {
        this.player = player;
        this.damage = damage;
    }

    public Player getPlayer() { return player; }
    public int getDamage() { return damage; }
}
