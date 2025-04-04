package me.calaritooo.event.events.player;

import me.calaritooo.player.Player;

public class PlayerJoinEvent {

    private final Player player;

    public PlayerJoinEvent(Player player) {
        this.player = player;
    }

    public Player getPlayer() { return player; }
}
