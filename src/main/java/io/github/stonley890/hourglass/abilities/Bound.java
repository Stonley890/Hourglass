package io.github.stonley890.hourglass.abilities;

import io.github.stonley890.hourglass.Ability;
import io.github.stonley890.hourglass.player.PlayerMemory;
import io.github.stonley890.hourglass.player.PlayerUtility;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

public class Bound extends Ability implements Listener {
    final String name = "Bound";
    final String lore = "With great strength, you are able to leap great heights with little effort.";
    final int levels = 3;
    final BaseComponent[] details = new ComponentBuilder().append("You have togglable Jump Boost ").color(ChatColor.BLACK).append("1").color(ChatColor.GREEN).append(" / ").color(ChatColor.BLACK).append("2").color(ChatColor.BLUE).append(" / ").color(ChatColor.BLACK).append("6").color(ChatColor.DARK_PURPLE).append(".").color(ChatColor.BLACK).create();
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
    public Bound() {}

    @Override
    public void init(Player player) {}
    @Override
    public void loop(Player player) {}

    @EventHandler
    public void playerToggleSneakEvent(@NotNull PlayerToggleSneakEvent event) {

        Player player = event.getPlayer();
        PlayerMemory memory = PlayerUtility.getPlayerMemory(player);

        if (event.isSneaking()) {

            if (memory.getAbility(this) == 1) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, PotionEffect.INFINITE_DURATION, 0, true, false, true));
            } else if (memory.getAbility(this) == 2) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, PotionEffect.INFINITE_DURATION, 1, true, false, true));
            } else if (memory.getAbility(this) == 3) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, PotionEffect.INFINITE_DURATION, 5, true, false, true));
            }

        } else {

            if (memory.getAbility(this) > 0) {
                removeInfiniteEffect(player, PotionEffectType.JUMP);
            }
        }
    }
}
