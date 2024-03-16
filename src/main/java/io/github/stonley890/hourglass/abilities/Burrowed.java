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

public class Burrowed extends Ability {

    final String name = "Burrowed";
    final String lore = "Weakened by the surface world, your habitat of refuge remains the only solace you know.";
    final int levels = 1;
    final BaseComponent[] details = new ComponentBuilder().append("While exposed to the sky, you have Weakness ").color(ChatColor.BLACK).append("1").color(ChatColor.GREEN).append(".").color(ChatColor.BLACK).create();
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
    public Burrowed() {}

    @Override
    public void init(Player player) {

    }

    @Override
    public void loop(Player player) {
        PlayerMemory memory = PlayerUtility.getPlayerMemory(player);

        if (memory.getAbility(this) == 1) {
            // If skylight level is 15, apply weakness. Otherwise, remove
            if (player.getLocation().getBlock().getLightFromSky() == 15) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, PotionEffect.INFINITE_DURATION, 0, true, false, true));
            } else {
                removeInfiniteEffect(player, PotionEffectType.WEAKNESS);
            }
        } else if (memory.getAbility(this) == 0) {
            removeInfiniteEffect(player, PotionEffectType.WEAKNESS);
        }
    }
}
