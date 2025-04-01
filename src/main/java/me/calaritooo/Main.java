package me.calaritooo;

import javax.swing.*;
import java.io.*;
import java.util.HashMap;

public class Main {
    private static final String FILE_NAME = "players.txt";
    private static HashMap<String, Player> players = new HashMap<>();

    public static void main(String[] args) {

        // Load existing players
        loadPlayersFromFile();

        // Get current player //
        String playerName = JOptionPane.showInputDialog("Enter player name: ");
        if (playerName == null || playerName.isEmpty()) {
            System.exit(0);
        }

        // Create player object, either getting a current one by name or creating one //
        Player player = players.getOrDefault(playerName, new Player(playerName));
        players.put(playerName, player);

        // Begin simulation on safe thread //
        Runtime.getRuntime().addShutdownHook(new Thread(() -> savePlayersToFile()));

        SwingUtilities.invokeLater(() -> new SimulatorGui(player, players));

    }

    // File Handling //

    private static void savePlayersToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Player player : players.values()) {
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
                    players.put(player.getName(), player);
                }
            }
            System.out.println("Player data loaded.");
        } catch (IOException e) {
            System.out.println("Error loading players: " + e.getMessage());
        }
    }
}