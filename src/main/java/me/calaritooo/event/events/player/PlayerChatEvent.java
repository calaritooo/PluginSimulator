package me.calaritooo.event.events.player;

import me.calaritooo.event.Cancelable;
import me.calaritooo.player.Player;

public class PlayerChatEvent implements Cancelable {

    private final Player player;
    private final String message;
    private boolean canceled = false;

    public PlayerChatEvent(Player player, String message) {
        this.player = player;
        this.message = message;
    }

    public Player getPlayer() { return player; }
    public String getMessage() { return message; }

    @Override
    public boolean isCanceled() {
        return canceled;
    }
    @Override
    public void setCancelled(boolean canceled) {
        this.canceled = canceled;
    }

}
