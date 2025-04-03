package me.calaritooo;

import me.calaritooo.player.Player;
import me.calaritooo.player.PlayerManager;

import javax.swing.*;
import java.io.*;

public class Main {

    private static final String FILE_NAME = "players.txt";

    public static void main(String[] args) {

        // Load existing players
        loadPlayersFromFile();

        // Get current player //
        String playerName = JOptionPane.showInputDialog("Enter player name: ");
        if (playerName == null || playerName.isEmpty()) {
            System.exit(0);
        }
        Player player = PlayerManager.getOrCreate(playerName);

        // Save on shutdown //
        Runtime.getRuntime().addShutdownHook(new Thread(() -> savePlayersToFile()));

        // Start GUI on EDT //
        SwingUtilities.invokeLater(() -> new SimulatorGui(player));

    }

    // File Handling //
    private static void savePlayersToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Player player : PlayerManager.list()) {
                writer.write(player.toSaveString());
                writer.newLine();
            }
            System.out.println("Player data saved.");
        } catch (IOException e) {
            System.out.println("Error saving players: " + e.getMessage());
        }
    }
    private static void loadPlayersFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Player player = Player.fromSaveString(line);
                if (player != null) {
                    PlayerManager.add(player);
                }
            }
            System.out.println("Player data loaded.");
        } catch (IOException e) {
            System.out.println("Error loading players: " + e.getMessage());
        }
    }
}