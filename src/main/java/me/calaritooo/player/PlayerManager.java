package me.calaritooo.player;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.calaritooo.event.EventManager;
import me.calaritooo.event.events.player.*;

import java.io.*;
import java.util.Collection;
import java.util.HashMap;

public class PlayerManager {

    private static final File PLAYER_FOLDER = new File("players");
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final HashMap<String, Player> players = new HashMap<>();

    // SIMPLE RETURNS
    public static Player get(String playerName) {
        return players.get(playerName);
    }
    public static Player getOrCreate(String playerName) {
        Player player = players.computeIfAbsent(playerName, k -> new Player(playerName));
        EventManager.onEvent(new PlayerJoinEvent(player));
        return get(playerName);
    }
    public static void add(Player player) {
        players.put(player.getName(), player);
    }
    public static void remove(Player player) { players.remove(player.getName()); }
    public static boolean isEmpty() { return players.isEmpty(); }
    public static Collection<Player> list() { return players.values(); }
    public static void clear() { players.clear(); }
    public static boolean exists(String playerName) {return players.containsKey(playerName); }

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

    // File handling (JSON)
    public static void saveAll() {
        if (!PLAYER_FOLDER.exists()) {
            PLAYER_FOLDER.mkdirs();
        }
        for (Player player : players.values()) {
            File file = new File(PLAYER_FOLDER, player.getName() + ".json");
            try (Writer writer = new FileWriter(file)) {
                GSON.toJson(player, writer);
            } catch (IOException e) {
                System.out.println("Error saving player: " + e.getMessage());
                e.printStackTrace();
            }
        }
        System.out.println("Player data saved.");
    }

    public static void loadAll() {
        if (!PLAYER_FOLDER.exists()) return;

        File[] files = PLAYER_FOLDER.listFiles((dir, name) -> name.endsWith(".json"));
        if (files == null) return;

        for (File file : files) {
            try (Reader reader = new FileReader(file)) {
                Player player = GSON.fromJson(reader, Player.class);
                if (player != null) {
                    add(player);
                }
            } catch (IOException e) {
                System.out.println("Error loading player: " + e.getMessage());
                e.printStackTrace();
            }
        }
        System.out.println("Player data loaded.");
    }
}
