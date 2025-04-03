package me.calaritooo;

import me.calaritooo.player.Player;
import me.calaritooo.player.PlayerManager;

import javax.swing.*;
import java.io.*;

public class Main {

    private static final String FILE_NAME = "players.txt";

    public static void main(String[] args) {

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