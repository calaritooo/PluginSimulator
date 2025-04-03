package me.calaritooo.player;

import java.util.Collection;
import java.util.HashMap;

public class PlayerManager {

    private static final HashMap<String, Player> players = new HashMap<>();

    public static Player get(String playerName) {
        return players.get(playerName.toLowerCase());
    }
    public static Player getOrCreate(String playerName) {
        return players.computeIfAbsent(playerName.toLowerCase(), k -> new Player(playerName));
    }
    public static void add(Player player) {
        players.put(player.getName().toLowerCase(), player);
    }
    public static void remove(Player player) {
        players.remove(player.getName().toLowerCase());
    }
    public static boolean isEmpty() {
        return players.isEmpty();
    }
    public static Collection<Player> list() {
        return players.values();
    }
    public static void clear() {
        players.clear();
    }
    public static boolean exists(String playerName) {
        return players.containsKey(playerName.toLowerCase());
    }

    public static String listPlayersAsString() {
        if (players.isEmpty()) {
            return "There are no players saved.";
        }

        StringBuilder sb = new StringBuilder("Player list:\n");
        int index = 1;
        for (Player player : players.values()) {
            sb.append(index++).append(". ").append(player.getName()).append(" >\n");
            sb.append("  - Health: ").append(player.getHealth()).append("/").append(player.getMaxHealth()).append("\n");
            sb.append("  - Inventory: ").append(player.getInventory().isEmpty() ? "empty" : player.getInventory().toString()).append("\n");
        }
        return sb.toString();
    }
}
