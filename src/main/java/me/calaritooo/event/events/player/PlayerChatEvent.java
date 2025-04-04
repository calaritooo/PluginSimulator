package me.calaritooo.event.events.player;

import me.calaritooo.player.Player;

public class PlayerChatEvent {

    public final Player player;
    public final String message;

    public PlayerChatEvent(Player player, String message) {
        this.player = player;
        this.message = message;
    }

    public Player getPlayer() { return player; }
    public String getMessage() { return message; }
}
