package me.calaritooo.command;

import me.calaritooo.gui.IOProvider;
import me.calaritooo.player.Player;

public class InvCommand implements Command {
    
    @Override
    public String getName() {
        return "/inv";
    }
    
    @Override
    public String getDescription() {
        return "Use this command to view or manage an inventory.";
    }
    
    @Override
    public String getUsage() {
        return "Usage: /inv <give/take/clear> <item> <quantity>";
    }
    
    @Override
    public void execute(Player player, String[] args) {
        if (args.length == 4) {
            // give //
            if (args[1].equalsIgnoreCase("give")) {
                try {
                    String item = args[2];
                    int quantity = Integer.parseInt(args[3]);
                    player.addPlayerItem(item, quantity);
                    if (quantity > 1) {
                        IOProvider.send(quantity + " " + item + "s added to " + player.getName() + "'s inventory.");
                    } else {
                        IOProvider.send(quantity + " " + item + " added to " + player.getName() + "'s inventory.");
                    }
                } catch (NumberFormatException e) { IOProvider.send("Invalid quantity."); }

            // take //
            } else if (args[1].equalsIgnoreCase("take")) {
                try {
                    String item = args[2];
                    int quantity = Integer.parseInt(args[3]);
                    player.removePlayerItem(item, quantity);
                    if (quantity > 1) {
                        IOProvider.send(quantity + " " + item + "s removed from " + player.getName() + "'s inventory.");
                    } else {
                        IOProvider.send(quantity + " " + item + " removed from " + player.getName() + "'s inventory.");
                    }
                } catch (NumberFormatException e) {
                    IOProvider.send("Invalid quantity.");
                }
            } else { IOProvider.send(getUsage()); }

        // clear //
        } else if (args.length == 2 && args[1].equalsIgnoreCase("clear")) {
            player.clearInventory();
            IOProvider.send("Inventory cleared.");

        // view (no args) //
        } else if (args.length == 1) {
            player.displayInventory();
        } else { IOProvider.send(getUsage()); }
    }
}
