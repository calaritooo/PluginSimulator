package me.calaritooo.event.events.player;

import me.calaritooo.player.Player;

public class PlayerSpawnEvent {

    private final Player player;

    public PlayerSpawnEvent(Player player) {
        this.player = player;
    }

    public Player getPlayer() { return player; }
}
