package me.calaritooo.player;

import java.util.HashMap;

public class Player {

    // Class fields //
    String name;
    int maxHealth;
    int health;
    HashMap<String, Integer> inventory;

    // Constructor //
    public Player(String name) {
        this.name = name;
        this.maxHealth = 100;
        this.health = 100;
        this.inventory = new HashMap<>();
    }

    // Getters //
    public String getName() {
        return name;
    }
    public int getMaxHealth() {
        return maxHealth;
    }
    public int getHealth() {
        return health;
    }

    public HashMap<String, Integer> getInventory() {
        return inventory;
    }

    public String displayInventory() {
        return getName() + "'s inventory: " + (inventory.isEmpty() ? "empty" : inventory.toString());
    }

    // Base Detail Setters //
    public void setName(String name) {
        this.name = name;
    }
    public String setHealth(int health) {
        if (health >= 0 && health <= this.maxHealth) {
            this.health = health;
            return "Player " + getName() + "'s health set to " + health;
        } else {
            return "Invalid health value. Valid values are 0 to " + maxHealth + ".";
        }
    }
    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
        if (this.health > maxHealth) {
            this.health = maxHealth;
        }
    }

    // Player actions //
    public String onJoin(Player player) {
        return "[SERVER] Player " + getName() + " joined the game!";
    }
    public String onChat(Player player, String message) {
        return "[CHAT] " + getName() + ": " + message;
    }
    public String heal(int quantity) {
        this.health = Math.min(this.health + quantity, this.maxHealth);
        return "";
    }
    public String hurt(int quantity) {
        this.health = Math.max(this.health - quantity, 0);
        return this.health == 0 ? "Player" + this.name + " died!" : "";
    }

    // Inventory Methods //
    public String addPlayerItem(String item, int quantity) {
        inventory.put(item, inventory.getOrDefault(item, 0) + quantity);
        return "";
    }
    public String removePlayerItem(String item, int quantity) {
        if (inventory.containsKey(item)) {
            int currentQuantity = inventory.get(item);
            if (currentQuantity > quantity) {
                inventory.put(item, currentQuantity - quantity);
                return "";
            } else {
                inventory.remove(item);
                return "";
            }
        } else {
            return "Item not found in inventory.";
        }
    }
    public void clearInventory() {
        inventory.clear();
    }

    // Display Player Details //
    public String getStats() {
        return "Player: " + name + "\n" +
                "Health: " + health + "/" + maxHealth + "\n" +
                "Inventory: " + (inventory.isEmpty() ? "N/A" : inventory.toString());
    }

    // Player Data Handlers //
    public String toSaveString() {
        StringBuilder inventoryData = new StringBuilder();
        for (String item : inventory.keySet()) {
            inventoryData.append(item).append(":").append(inventory.get(item)).append(",");
        }
        String inventoryString = !inventoryData.isEmpty()
                ? inventoryData.substring(0, inventoryData.length() - 1) : "empty";

        return name + ";" + maxHealth + ";" + health + ";" + inventoryString;
    }
    public static Player fromSaveString(String data) {
        String[] parts = data.split(";");
        if (parts.length < 4) return null;

        Player player = new Player(parts[0]); // Player name
        player.setMaxHealth(Integer.parseInt(parts[1]));
        player.setHealth(Integer.parseInt(parts[2]));

        if (!parts[3].equals("empty")) {
            String[] items = parts[3].split(",");
            for (String itemEntry : items) {
                String[] itemData = itemEntry.split(":");
                player.addPlayerItem(itemData[0], Integer.parseInt(itemData[1]));
            }
        }
        return player;
    }
}
