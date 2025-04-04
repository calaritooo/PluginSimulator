package me.calaritooo.player;


import me.calaritooo.event.EventManager;
import me.calaritooo.event.events.player.PlayerDeathEvent;
import me.calaritooo.gui.IOProvider;

import java.util.HashMap;

public class Player {

    // Class fields //
    String name;
    int maxHealth;
    int health;
    boolean hasJoinedBefore = false;
    HashMap<String, Integer> inventory;

    // Constructors //
    public Player(String name) {
        this.name = name;
        this.maxHealth = 100;
        this.health = 100;
        this.inventory = new HashMap<>();
        this.hasJoinedBefore = true;
    }
    // For GSON
    public Player() {}

    // Getters //
    public String getName() {
        return name;
    }
    public boolean hasJoinedBefore() {
        return hasJoinedBefore;
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
        IOProvider.send(getName() + "'s inventory: " + (inventory.isEmpty() ? "empty" : inventory.toString()));
    }

    // Base Detail Setters //
    public void setName(String name) {
        this.name = name;
    }
    public void setHasJoinedBefore(boolean hasJoinedBefore) {
        this.hasJoinedBefore = hasJoinedBefore;
    }
    public void setHealth(int health) {
        if (health >= 0 && health <= this.maxHealth) {
            this.health = health;
        } else {
            IOProvider.send("Invalid health value. Valid values are 0 to " + maxHealth + ".");
        }
    }
    public void setMaxHealth(int maxHealth) {
        this.maxHealth = maxHealth;
        if (this.health > maxHealth) {
            this.health = maxHealth;
        }
    }

    // Player actions //
    public void heal(int quantity) {
        this.health = Math.min(this.health + quantity, this.maxHealth);
    }
    public void hurt(int quantity) {
        this.health = Math.max(this.health - quantity, 0);
        if (this.health == 0) { EventManager.onEvent(new PlayerDeathEvent(this));
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
            IOProvider.send("Item not found in inventory.");
        }
    }
    public void clearInventory() {
        inventory.clear();
    }

    // Display Player Details //
    public void getStats() {
        IOProvider.send("Player: " + name + "\n" +
                "Health: " + health + "/" + maxHealth + "\n" +
                "Inventory: " + (inventory.isEmpty() ? "N/A" : inventory.toString()));
    }
}
