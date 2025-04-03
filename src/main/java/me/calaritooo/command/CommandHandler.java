package me.calaritooo.command;

import me.calaritooo.player.Player;

import java.util.HashMap;
import java.util.Map;

public class CommandHandler {

    private static final Map<String, Command> commandMap = new HashMap<>();

    public CommandHandler() {
        registerCommand(new ExitCommand());
        registerCommand(new InvCommand());
        registerCommand(new StatsCommand());
        registerCommand(new ListCommand());
        registerCommand(new HealCommand());
    }

    public void registerCommand(Command command) {
        commandMap.put(command.getName().toLowerCase(), command);
    }

    public String handleCommand(Player player, String input) {
        String[] args = input.split(" ");
        String commandName = args[0].toLowerCase();
        Command command = commandMap.get(commandName.toLowerCase());
        if (command != null) {return command.execute(player, args);} else {return "Unknown command: " + args[0];}
    }

    public String getHelp() {
        StringBuilder help = new StringBuilder("Available commands:\n");
        for (Command command : commandMap.values()) {
            help.append(command.getName()).append(" - ").append(command.getDescription()).append("\n   - ")
                    .append(command.getUsage()).append("\n");
        }
        return help.toString();
    }
}
