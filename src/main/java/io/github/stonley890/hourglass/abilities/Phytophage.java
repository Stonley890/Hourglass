package io.github.stonley890.hourglass.abilities;

import io.github.stonley890.hourglass.Ability;
import io.github.stonley890.hourglass.Hourglass;
import io.github.stonley890.hourglass.player.PlayerMemory;
import io.github.stonley890.hourglass.player.PlayerUtility;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Phytophage extends Ability implements Listener {

    final String name = "Phytophage";
    final String lore = "You find that, upon eating meat, you are left weaker than before.";
    final int levels = 1;
    final BaseComponent[] details = new ComponentBuilder().append("You suffer from ").color(ChatColor.BLACK).append("Nausea, Hunger, and Weakness").color(ChatColor.GREEN).append(" for 12 s when eating meat. You gain ").color(ChatColor.BLACK).append("Saturation 2").color(ChatColor.GREEN).append(" when eating non-meat foods.").color(ChatColor.BLACK).create();
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

    }

    @EventHandler
    public void onEat(@NotNull PlayerItemConsumeEvent event) {
        Player player = event.getPlayer();
        PlayerMemory memory = PlayerUtility.getPlayerMemory(player);

        if (memory.getAbility(this) == 1) {
            if (Hourglass.meatFoods.contains(event.getItem().getType())) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 20*12, 0, true));
                player.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 20*12, 0, true));
                player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 20*12, 0, true));
            } else {
                player.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 20*12, 1, true));
            }
        }

    }

}
