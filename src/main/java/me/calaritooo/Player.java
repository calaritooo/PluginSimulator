package me.calaritooo;

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

    public void displayInventory() {
        System.out.println(getName() + "'s inventory: " + (inventory.isEmpty() ? "empty" : inventory.toString()));
    }

    // Base Detail Setters //
    public void setName(String name) {
        this.name = name;
    }
    public void setHealth(int health) {
        if (health >= 0 && health <= this.maxHealth) {
            this.health = health;
        } else {
            System.out.println("Invalid health value. Valid values are 0 to " + maxHealth + ".");
        }
    }
    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
        if (this.health > maxHealth) {
            this.health = maxHealth;
        }
    }

    // Player actions //
    public void heal(int quantity){
        this.health = Math.min(this.health + quantity, this.maxHealth);
    }
    public void hurt(int quantity){
        this.health = Math.max(this.health - quantity, 0);
        if (this.health == 0) {
            System.out.println("Player " + this.name + " died!");
        }
    }

    // Inventory Methods //
    public void addPlayerItem(String item, int quantity) {
        inventory.put(item, inventory.getOrDefault(item, 0) + quantity);
    }
    public void removePlayerItem(String item, int quantity) {
        if (inventory.containsKey(item)) {
            int currentQuantity = inventory.get(item);
            if (currentQuantity > quantity) {
                inventory.put(item, currentQuantity - quantity);
            } else {
                inventory.remove(item);
            }
        } else {
            System.out.println("Item not found in inventory.");
        }
    }
    public void clearInventory() {
        inventory.clear();
    }

    // Display Player Details //
    public void displayStats() {
        System.out.println("Player: " + name);
        System.out.println("Health: " + health + "/" + maxHealth);
        System.out.println("Inventory: " + (inventory.isEmpty() ? "N/A" : inventory.toString()));
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
    public static void listPlayers(HashMap<String, Player> players) {
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
}
