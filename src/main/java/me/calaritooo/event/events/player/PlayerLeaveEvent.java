package me.calaritooo.event.events.player;

import me.calaritooo.player.Player;

public class PlayerLeaveEvent {

    private final Player player;

    public PlayerLeaveEvent(Player player) {
        this.player = player;
    }

    public Player getPlayer() { return player; }
}
