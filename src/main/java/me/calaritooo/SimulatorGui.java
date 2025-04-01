package me.calaritooo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;

public class SimulatorGui {

    private JFrame frame;
    private JTextArea outputArea;
    private JTextField inputField;
    private Player player;
    private final HashMap<String, Player> players;

    public SimulatorGui(Player player, HashMap<String, Player> players) {
        this.player = player;
        this.players = players;
        createGUI();
    }

    private void createGUI() {
        frame = new JFrame("Plugin Simulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        inputField = new JTextField();
        inputField.addActionListener(e -> handleCommand(inputField.getText()));

        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.getContentPane().add(inputField, BorderLayout.SOUTH);

        frame.setVisible(true);
        appendOutput("Welcome " + player.getName() + "! >");
    }

    private void appendOutput(String message) {
        outputArea.append(message + "\n");
    }

    private void handleCommand(String input) {
        inputField.setText("");
        appendOutput("> " + input);
        String playerName = player.getName();

        String[] arguments = input.trim().toLowerCase().split(" ");

        // Command handler
        switch (arguments[0]) {
            case "/help":
                System.out.println("Available commands:");
                System.out.println("/help");
                System.out.println("/players");
                System.out.println("/stats <max_health/health> set <value>");
                System.out.println("/inv <give/take/clear> <item> <qty>");
                System.out.println("/heal <qty>");
                System.out.println("/hurt <qty>");
                System.out.println("/exit");
                break;
            case "/players":
                Player.listPlayers(players);
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
                appendOutput("Exiting simulator...");
                System.exit(0);
                break;
            default:
                System.out.println("Unknown command: " + arguments[0]);
                break;
        }
    }
}
