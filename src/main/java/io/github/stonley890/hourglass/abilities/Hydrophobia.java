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

public class Hydrophobia extends Ability {
    final String name = "Hydrophobia";
    final String lore = "The awful sting of water leaves you scarred… but not nearly as much as your fear of it.";
    final int levels = 2;
    final BaseComponent[] details = new ComponentBuilder().append("While in rain or in water, you take ").color(ChatColor.BLACK).append("1").color(ChatColor.GREEN).append("/").color(ChatColor.BLACK).append("2").color(ChatColor.BLUE).append(" damage per second. ").color(ChatColor.BLACK).append("Rain damage is negated by headgear.").color(ChatColor.GREEN).create();
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
            if ((player.getLocation().getBlock().getLightFromSky() == 15 && !player.getWorld().isClearWeather() && player.getInventory().getHelmet() == null) || player.isInWater()) {
                player.damage(memory.getAbility(this));
            }
        }
    }
}
