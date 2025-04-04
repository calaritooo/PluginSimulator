package me.calaritooo.command;

import me.calaritooo.util.MessageBus;
import me.calaritooo.player.Player;
import me.calaritooo.player.PlayerManager;

public class ListCommand implements Command {

    @Override
    public String getName() {
        return "/list";
    }

    @Override
    public String getDescription() {
        return "This command lists all registered players";
    }

    @Override
    public String getUsage() {
        return "Usage: /list";
    }

    @Override
    public void execute(Player player, String[] args) {
        MessageBus.send(PlayerManager.listPlayersAsString());
    }
}
