package io.github.stonley890.hourglass.player;

import io.github.stonley890.hourglass.Ability;
import io.github.stonley890.hourglass.Hourglass;
import io.github.stonley890.hourglass.commands.Abilities;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PlayerUtility {
    private static final Map<String, PlayerMemory> playerMemory = new HashMap<>();

    private PlayerUtility() {
        throw new IllegalStateException("Utility class");
    }

    public static PlayerMemory getPlayerMemory(@NotNull Player player) {
        if(!playerMemory.containsKey(player.getUniqueId().toString())) {
            PlayerMemory memory = new PlayerMemory();
            playerMemory.put(player.getUniqueId().toString(), memory);
            return memory;
        }
        return playerMemory.get(player.getUniqueId().toString());
    }

    public static void fetchPlayerMemory(Player player) {
        PlayerMemory memory = new PlayerMemory();
        List<Integer> abilitiesList = loadFromFile(player).getIntegerList("abilities");
        if (abilitiesList.size() == Abilities.abilities.size()) memory.playerAbilities = abilitiesList;
        playerMemory.put(player.getUniqueId().toString(), memory);
    }

    @NotNull
    public static FileConfiguration loadFromFile(@NotNull Player player) {
        File file = new File(Hourglass.getPlugin().getDataFolder().getAbsolutePath() + "/player/" + player.getUniqueId() + ".yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        return config;
    }

    public static void saveToFile(@NotNull Player player) {
        File file = new File(Hourglass.getPlugin().getDataFolder().getAbsolutePath() + "/player/" + player.getUniqueId() + ".yml");
        FileConfiguration config = new YamlConfiguration();
        config.set("abilities", getPlayerMemory(player).playerAbilities);
        try {
            config.save(file);
        } catch (IOException e) {
            Bukkit.getLogger().severe("Could not save!");
        }
    }

}
