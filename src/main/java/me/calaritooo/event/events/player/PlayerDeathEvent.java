package me.calaritooo.event.events.player;

import me.calaritooo.player.Player;

public class PlayerDeathEvent {

    public final Player player;

    public PlayerDeathEvent(Player player) {
        this.player = player;
    }

    public Player getPlayer() { return player; }
}
