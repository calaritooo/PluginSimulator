package me.calaritooo.event.events.player;

import me.calaritooo.player.Player;

public class PlayerWelcomeEvent {

    private final Player player;

    public PlayerWelcomeEvent(Player player) {
        this.player = player;
    }

    public Player getPlayer() { return player; }
}
