package io.github.stonley890.hourglass.abilities;

import io.github.stonley890.hourglass.Ability;
import io.github.stonley890.hourglass.player.PlayerMemory;
import io.github.stonley890.hourglass.player.PlayerUtility;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class Heatwave extends Ability {
    final String name = "Heatwave";
    final String lore = "Exposure to heat hinders you to the point of exhaustion… or worse.";
    final int levels = 2;
    final BaseComponent[] details = new ComponentBuilder().append("While in hot biomes, you gain Weakness and Slowness ").color(ChatColor.BLACK).append("1").color(ChatColor.GREEN).append(" / ").color(ChatColor.BLACK).append("2").color(ChatColor.BLUE).append(".").color(ChatColor.BLACK).create();
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
            if (player.getLocation().getBlock().getTemperature() > 1.0) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 40, -1+memory.getAbility(this), true));
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, -1+memory.getAbility(this), true));
            }
        }
    }
}
