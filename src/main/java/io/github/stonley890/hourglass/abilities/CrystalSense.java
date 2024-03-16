package io.github.stonley890.hourglass.abilities;

import io.github.stonley890.hourglass.Ability;
import io.github.stonley890.hourglass.player.PlayerMemory;
import io.github.stonley890.hourglass.player.PlayerUtility;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class CrystalSense extends Ability {

    final String name = "Crystal Sense";
    final String lore = "The mysterious power of End Crystals grants you regenerative properties.";
    final int levels = 1;
    final BaseComponent[] details = new ComponentBuilder().append("End Crystals within 24 m grant you ").color(ChatColor.BLACK).append("Regeneration 1").color(ChatColor.GREEN).append(" and ").color(ChatColor.BLACK).append("Glowing").color(ChatColor.GREEN).append(". The effect lingers for 6 seconds upon leaving.").color(ChatColor.BLACK).create();
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

        if (memory.getAbility(this) == 1) {
            for (Entity entity : player.getNearbyEntities(24, 24, 24)) {
                if (entity.getType().equals(EntityType.ENDER_CRYSTAL)) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 120, 0, true, false, true));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 120, 0, true, false, true));

                    break;
                }
            }
        }
    }
}
