package me.calaritooo.event.listeners;

import me.calaritooo.command.Command;
import me.calaritooo.event.Listener;
import me.calaritooo.event.events.player.*;
import me.calaritooo.player.Player;

public class PlayerListener implements Listener {

    @Override
    public void handle(Object event) {
        if (event instanceof PlayerWelcomeEvent welcomeEvent) {
            Player p = welcomeEvent.getPlayer();
            p.send("[SERVER] Welcome " + p.getName() + "to the game!");
        }

        if (event instanceof PlayerJoinEvent joinEvent) {
            Player p = joinEvent.getPlayer();
            p.send("[SERVER] Player " + p.getName() + " joined the game!");
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
            Command c = commandEvent.getCommand();
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
