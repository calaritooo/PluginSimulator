package me.calaritooo;

import me.calaritooo.command.CommandHandler;
import me.calaritooo.gui.SimulatorIO;
import me.calaritooo.player.Player;

import javax.swing.*;
import java.awt.*;

public class SimulatorGui implements SimulatorIO {

    private JFrame frame;
    private JTextArea outputArea;
    private JTextField inputField;
    private final Player player;
    private final CommandHandler commandHandler = new CommandHandler();

    public SimulatorGui(Player player) {
        this.player = player;
        createGUI();
    }

    // GUI layout
    private void createGUI() {
        frame = new JFrame("Plugin Simulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        outputArea = new JTextArea();
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        inputField = new JTextField();
        inputField.addActionListener(e -> handleInput(inputField.getText()));

        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
        frame.getContentPane().add(inputField, BorderLayout.SOUTH);

        frame.setVisible(true);
        send(player.onJoin(player));
    }

    // Message output
    @Override
    public void send(String message) {
        outputArea.append(message + "\n");
    }

    private void handleInput(String input) {
        inputField.setText("");
        if (input.charAt(0) == '/') {
            send("> " + input);
            handleCommand(input);
        } else {
            send(player.onChat(player, input));
        }
    }

    // Command handler
    private void handleCommand(String input) {
        if (input.equalsIgnoreCase("/help")) {
            commandHandler.getHelp(player);
            return;
        }
        commandHandler.handleCommand(player, input);
    }
}
