package io.github.stonley890.hourglass.abilities;

import io.github.stonley890.hourglass.Ability;
import io.github.stonley890.hourglass.Hourglass;
import io.github.stonley890.hourglass.player.PlayerMemory;
import io.github.stonley890.hourglass.player.PlayerUtility;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public class TheHourglass extends Ability {
    final String name = "The Hourglass";
    final String lore = "What is this…?";
    final int levels = 1;
    final BaseComponent[] details = new ComponentBuilder().append("You are given an ").color(ChatColor.BLACK).append("Ancient Hourglass").italic(true).append(" that, upon it leaving your inventory, will kill you. Upon reaching low health, you will be given ").italic(false).append("Instant Health and Regeneration").color(ChatColor.GREEN).append(" for 10 seconds every 20 seconds.").color(ChatColor.BLACK).create();
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
        PlayerMemory memory = PlayerUtility.getPlayerMemory(player);

        if (memory.getAbility(this) == 0) {
            player.getInventory().remove(Material.KNOWLEDGE_BOOK);
        } else {
            player.getInventory().addItem(Hourglass.hourglassItem);
        }
    }

    @Override
    public void loop(Player player) {
        PlayerMemory memory = PlayerUtility.getPlayerMemory(player);

        if (memory.getAbility(this) == 1) {
            if (!(player.getInventory().contains(Hourglass.hourglassItem) || player.getItemOnCursor().equals(Hourglass.hourglassItem))) player.damage(1000);
        }
    }

    public void loop20(Player player) {
        PlayerMemory memory = PlayerUtility.getPlayerMemory(player);

        if (memory.getAbility(this) == 1) {
            if (player.getHealth() < 7) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 20*10, 0, true));
                player.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 20*10, 0, true));
            }
        }
    }

    @EventHandler
    public void playerDeathEvent(@NotNull PlayerDeathEvent event) {
        Player player = event.getEntity();
        PlayerMemory memory = PlayerUtility.getPlayerMemory(player);

        if (memory.getAbility(this) == 1) {
            player.getInventory().remove(Hourglass.hourglassItem);
        }
    }
}
