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

public class Crepescule extends Ability {

    final String name = "Crepuscule";
    final String lore = "Your senses are focused for the late hours of dusk, where peril may lie at any distance.";
    final int levels = 1;
    final BaseComponent[] details = new ComponentBuilder().append("You have permanent Night Vision ").color(ChatColor.BLACK).append("1").color(ChatColor.GREEN).append(".").color(ChatColor.BLACK).create();
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
            removeInfiniteEffect(player, PotionEffectType.NIGHT_VISION);
        } else if (memory.getAbility(this) == 1) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, PotionEffect.INFINITE_DURATION, 0, true, false, false));
        }
    }

    @Override
    public void loop(Player player) {

    }
}
