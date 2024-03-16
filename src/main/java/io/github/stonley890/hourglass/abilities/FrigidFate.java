package io.github.stonley890.hourglass.abilities;

import io.github.stonley890.hourglass.Ability;
import io.github.stonley890.hourglass.player.PlayerMemory;
import io.github.stonley890.hourglass.player.PlayerUtility;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class FrigidFate extends Ability {
    final String name = "Frigid Fate";
    final String lore = "As the cold envelopes you, you rapidly begin to fall victim to its effects.";
    final int levels = 2;
    final BaseComponent[] details = new ComponentBuilder().append("While in cold biomes, you gain Weakness and Slowness ").color(ChatColor.BLACK).append("1").color(ChatColor.GREEN).append("/").color(ChatColor.BLACK).append("2").color(ChatColor.BLUE).append(".").color(ChatColor.BLACK).create();
    @Override
    public String getName() {
        return name;
    }
    @Override
    public String getLore() {
        return lore;
    }
    @Override
    public int getLevels() {
        return levels;
    }
    @Override
    public BaseComponent[] getDetails() {
        return details;
    }
    @Override
    public void init(Player player) {

    }

    @Override
    public void loop(Player player) {
        PlayerMemory memory = PlayerUtility.getPlayerMemory(player);

        if (memory.getAbility(this) > 0) {
            if (player.getLocation().getBlock().getTemperature() < 0.2) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 40, -1+memory.getAbility(this), true));
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, -1+memory.getAbility(this), true));
            }
        }
    }
}
