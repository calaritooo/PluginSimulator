package me.calaritooo.event.listeners;

import me.calaritooo.event.EventManager;
import me.calaritooo.event.Listener;
import me.calaritooo.event.events.player.*;
import me.calaritooo.util.MessageBus;
import me.calaritooo.player.Player;

public class PlayerListener implements Listener {

    // When listener reached 3 usages, must be moved to own class file!
    @Override
    public void handle(Object event) {
        if (event instanceof PlayerWelcomeEvent welcomeEvent) {
            Player p = welcomeEvent.getPlayer();
            MessageBus.send("[SERVER] Welcome " + p.getName() + " to the engine! Type '/help' to see a list of available commands.");
        }

        if (event instanceof PlayerJoinEvent joinEvent) {
            Player p = joinEvent.getPlayer();
            MessageBus.send("[SERVER] Player " + p.getName() + " has joined the engine!");
            if (!p.hasJoinedBefore()) {
                EventManager.onEvent(new PlayerWelcomeEvent(p));
                p.setHasJoinedBefore(true);
            }
        }

        if (event instanceof PlayerLeaveEvent leaveEvent) {
            Player p = leaveEvent.getPlayer();
        }

        if (event instanceof PlayerChatEvent chatEvent) {
            Player p = chatEvent.getPlayer();
            String message = chatEvent.getMessage();
        }

        if (event instanceof PlayerCommandEvent commandEvent) {
            Player p = commandEvent.getPlayer();
            String c = commandEvent.getCommand();
        }

        if (event instanceof PlayerSpawnEvent spawnEvent) {
            Player p = spawnEvent.getPlayer();
        }

        if (event instanceof PlayerDeathEvent deathEvent) {
            Player p = deathEvent.getPlayer();
        }

        if (event instanceof PlayerTakeDamageEvent takeDamageEvent) {
            Player p = takeDamageEvent.getPlayer();
            int d = takeDamageEvent.getDamage();
        }

        if (event instanceof PlayerDoDamageEvent doDamageEvent) {
            Player p = doDamageEvent.getPlayer();
            int d = doDamageEvent.getDamage();
        }
    }
}
