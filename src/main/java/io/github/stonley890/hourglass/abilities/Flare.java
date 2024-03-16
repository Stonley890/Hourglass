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

public class Flare extends Ability {
    final String name = "Flare";
    final String lore = "As you enter more and more danger, you feel the intense yet rejuvenating burn of fury ignite in your heart.";
    final int levels = 1;
    final BaseComponent[] details = new ComponentBuilder().append("While at low health, you gain ").color(ChatColor.BLACK).append("Regeneration 1").color(ChatColor.GREEN).append(" and ").color(ChatColor.BLACK).append("Fire Resistance").color(ChatColor.GREEN).append(" for 10 s every 20 s.").color(ChatColor.BLACK).create();
    @Override
    public void init(Player player) {
        
    }

    @Override
    public void loop(Player player) {
        
    }
    
    public void loop20(Player player) {
        PlayerMemory memory = PlayerUtility.getPlayerMemory(player);

        if (memory.getAbility(this) == 1) {
            if (player.getHealth() < 7) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 20*10, 0, true));
                player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 20*10, 0, true));
            }
        }
    }

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
}
