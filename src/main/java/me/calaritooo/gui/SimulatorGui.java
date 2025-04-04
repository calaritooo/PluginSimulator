package me.calaritooo.gui;

import me.calaritooo.command.CommandHandler;
import me.calaritooo.event.EventManager;
import me.calaritooo.event.events.player.PlayerChatEvent;
import me.calaritooo.event.events.player.PlayerCommandEvent;
import me.calaritooo.event.events.player.PlayerJoinEvent;
import me.calaritooo.player.Player;
import me.calaritooo.util.MessageBus;
import me.calaritooo.util.MessageReceiver;

import javax.swing.*;
import java.awt.*;

public class SimulatorGui implements MessageReceiver {

    private JFrame frame;
    private JTextArea outputArea;
    private JTextField inputField;
    private final Player player;
    private final CommandHandler commandHandler = new CommandHandler();

    public SimulatorGui(Player player) {
        MessageBus.set(this);
        this.player = player;
        createGUI();
        EventManager.onEvent(new PlayerJoinEvent(player));
    }

    // GUI layout
    private void createGUI() {
        frame = new JFrame("Engine Test");
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
    }

    // Message output (Without MessageBus, since it's directly to the GUI)
    @Override
    public void send(String message) {
        outputArea.append(message + "\n");
    }

    // Hand input as chat or command
    private void handleInput(String input) {
        if (input == null || input.isEmpty()) {
            return;
        }
        inputField.setText("");
        if (input.charAt(0) == '/') {
            send("> " + input);
            handleCommand(input);
            EventManager.onEvent(new PlayerCommandEvent(player, input));
        } else {
            send("[CHAT] " + player.getName() + ": " + input);
            EventManager.onEvent(new PlayerChatEvent(player, input));
        }
    }

    // Command handler
    private void handleCommand(String input) {
        if (input.equalsIgnoreCase("/help")) {
            commandHandler.getHelp();
            return;
        }
        commandHandler.handleCommand(player, input);
    }
}
