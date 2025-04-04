package me.calaritooo;

import me.calaritooo.event.EventManager;
import me.calaritooo.event.listeners.PlayerListener;
import me.calaritooo.gui.SimulatorGui;
import me.calaritooo.player.Player;
import me.calaritooo.player.PlayerManager;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        // Register listeners
        EventManager.registerListener(new PlayerListener());
        System.out.println("Event listeners successfully registered");

        // Load existing players
        PlayerManager.loadAll();

        // Get current player
        String playerName = JOptionPane.showInputDialog("Enter player name: ");
        if (playerName == null || playerName.isEmpty()) {
            System.exit(0);
        }
        Player player = PlayerManager.getOrCreate(playerName);

        // Save on shutdown
        Runtime.getRuntime().addShutdownHook(new Thread(PlayerManager::saveAll));

        // Start GUI on EDT
        SwingUtilities.invokeLater(() -> new SimulatorGui(player));

    }
}