package me.calaritooo;

import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    private static final String FILE_NAME = "players.txt";
    private static HashMap<String, Player> players = new HashMap<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Load existing players
        loadPlayersFromFile();

        System.out.println("Plugin simulator successfully initialized.");
        System.out.println("Type '/exit' to stop the simulator and save.");
        System.out.println("Enter the player's name: ");
        String playerName = scanner.nextLine();

        Player player = players.getOrDefault(playerName, new Player(playerName));
        players.put(playerName, player);

        System.out.println("Welcome, " + playerName + ", to the plugin simulator.");
        System.out.println("Type '/help' to see all available commands.");
        boolean simRunning = true;

        while (simRunning) {
            System.out.println("> ");
            String input = scanner.nextLine().trim().toLowerCase();
            String[] arguments = input.split(" ");

            switch (arguments[0]) {
                case "/help":
                    System.out.println("Available commands:");
                    System.out.println("/help");
                    System.out.println("/players");
                    System.out.println("/stats");
                    System.out.println("/inv");
                    System.out.println("/heal");
                    System.out.println("/hurt");
                    System.out.println("/exit");
                    break;
                case "/players":
                    listPlayers();
                    break;
                case "/stats":
                    if (arguments.length == 1) {
                        player.displayStats();
                    } else if (arguments.length == 4 && arguments[2].equals("set")) {
                        try {
                            String stat = arguments[1];
                            int value = Integer.parseInt(arguments[3]);
                            switch (stat) {
                                case "max_health":
                                    player.setMaxHealth(value);
                                    System.out.println("Max health set to " + value + ".");
                                    break;
                                case "health":
                                    player.setHealth(value);
                                    System.out.println("Health set to " + value + ".");
                                    break;
                                default:
                                    System.out.println("Unknown stat: " + stat);
                                    break;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid value: " + arguments[1]);
                        }
                    } else {
                        System.out.println("Usage: /stats <max_health/health> set <value>");
                    }
                    break;
                case "/inv":
                    if (arguments.length == 4) {
                        if (arguments[1].equalsIgnoreCase("give")) {
                            try {
                                String item = arguments[2];
                                int quantity = Integer.parseInt(arguments[3]);
                                player.addPlayerItem(item, quantity);
                                if (quantity > 1) {
                                    System.out.println(quantity + " " + item + "s added to " + playerName + "'s inventory.");
                                } else {
                                    System.out.println(quantity + " " + item + " added to " + playerName + "'s inventory.");
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid quantity.");
                            }
                        } else if (arguments[1].equalsIgnoreCase("take")) {
                            try {
                                String item = arguments[2];
                                int quantity = Integer.parseInt(arguments[3]);
                                player.removePlayerItem(item, quantity);
                                if (quantity > 1) {
                                    System.out.println(quantity + " " + item + "s removed from " + playerName + "'s inventory.");
                                } else {
                                    System.out.println(quantity + " " + item + " removed from " + playerName + "'s inventory.");
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Invalid quantity.");
                            }
                        }
                    } else if (arguments.length == 2 && arguments[1].equalsIgnoreCase("clear")) {
                        player.clearInventory();
                        System.out.println("Inventory cleared.");
                    } else if (arguments.length == 1) {
                        player.displayInventory();
                    } else {
                        System.out.println("Usage: /inv <give/take/clear> <item> <quantity>");
                    }
                    break;
                case "/heal":
                    if (arguments.length == 2) {
                        player.heal(Integer.parseInt(arguments[1]));
                    } else {
                        System.out.println("Usage: /heal <quantity>");
                    }
                    break;
                case "/hurt":
                    if (arguments.length == 2) {
                        player.hurt(Integer.parseInt(arguments[1]));
                    } else {
                        System.out.println("Usage: /hurt <quantity>");
                    }
                    break;
                case "/exit":
                    simRunning = false;
                    break;
                default:
                    System.out.println("Unknown command: " + arguments[0]);
                    break;
            }
        }

        savePlayersToFile();

        System.out.println("Shutting down Plugin Simulator...");
        System.out.println("Plugin simulator successfully shut down.");
        scanner.close();
    }

    public static void listPlayers(){
        if (players.isEmpty()) {
            System.out.println("There are no players saved.");
        } else {
            System.out.println("Player list: ");
            int index = 1;
            for (Player player : players.values()) {
                System.out.println(index + ". " + player.getName() + " >");
                System.out.println("  - Health: " + player.getHealth() + "/" + player.getMaxHealth());
                System.out.println("  - Inventory: " + (player.getInventory().isEmpty() ? "empty" : player.getInventory().toString()));
                index++;
            }
        }
    }

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