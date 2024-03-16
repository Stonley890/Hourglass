package io.github.stonley890.hourglass.abilities;

import io.github.stonley890.hourglass.Ability;
import io.github.stonley890.hourglass.player.PlayerMemory;
import io.github.stonley890.hourglass.player.PlayerUtility;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.jetbrains.annotations.NotNull;

public class EnderiansPacifist extends Ability implements Listener {
    final String name = "Enderian’s Pacifist";
    final String lore = "A true ally to the Enderian ways, you find yourself unwilling — or unable? — to utilize their fragments for personal gain.";
    final int levels = 1;
    final BaseComponent[] details = new ComponentBuilder().append("You are unable to teleport using ender pearls. Chorus fruit teleportation is limited to only 1 block.").color(ChatColor.BLACK).create();
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
    public void entityTargetLivingEntityEvent(EntityTargetLivingEntityEvent event) {

        try {
            if (event.getEntity().getType().equals(EntityType.ENDERMAN) && event.getTarget().getType().equals(EntityType.PLAYER)) {

                Player player = (Player) event.getTarget();
                PlayerMemory memory = PlayerUtility.getPlayerMemory(player);

                // Enderian's Pacifist
                if (memory.getAbility(this) == 1) {
                    event.setCancelled(true);
                }

            }
        } catch (NullPointerException e) {
            // Target type cannot be determined (irrelevant)
        }
    }

    @EventHandler
    public void projectileLaunchEvent(@NotNull ProjectileLaunchEvent event) {


        if (event.getEntity().getShooter() instanceof Player) {

            Player player = (Player) event.getEntity().getShooter();
            PlayerMemory memory = PlayerUtility.getPlayerMemory(player);

            if (event.getEntity().getType().equals(EntityType.ENDER_PEARL) && memory.getAbility(this) == 1) {
                event.setCancelled(true);
            }
        }
    }
}
