package io.github.stonley890.hourglass.abilities;

import io.github.stonley890.hourglass.Ability;
import io.github.stonley890.hourglass.Hourglass;
import io.github.stonley890.hourglass.player.PlayerMemory;
import io.github.stonley890.hourglass.player.PlayerUtility;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

public class EnderiansEmbrace extends Ability implements Listener {

    final String name = "Enderian’s Embrace";
    final String lore = "You rest well with your Enderian traits, capable of calling on your abilities at will.";
    final int levels = 1;
    final BaseComponent[] details = new ComponentBuilder().append("You have access to ").color(ChatColor.BLACK).append("infinite ender pearls").color(ChatColor.GREEN).append(". This overrides Enderian’s Pacifist.").color(ChatColor.BLACK).create();
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

        if (memory.getAbility(this) == 1) {
            if (!player.getInventory().contains(Material.ENDER_PEARL)) {
                player.getInventory().addItem(new ItemStack(Material.ENDER_PEARL, 1));
            }
        }
    }

    @Override
    public void loop(Player player) {

    }

    @EventHandler
    public void playerDropItemEvent(@NotNull PlayerDropItemEvent event) {

        Player player = event.getPlayer();
        PlayerMemory memory = PlayerUtility.getPlayerMemory(player);

        if (event.getItemDrop().getItemStack().getType().equals(Material.ENDER_PEARL) && memory.getAbility(this) == 1) {

            int pearlsHeld = 0;

            for (ItemStack item : player.getInventory().getContents()) {
                if (item != null) {
                    if (item.getType().equals(Material.ENDER_PEARL)) {
                        pearlsHeld = pearlsHeld + item.getAmount();
                    }
                }
            }

            if (pearlsHeld == 0) {
                Bukkit.getScheduler().runTask(Hourglass.getPlugin(), (Runnable) new BukkitRunnable() {
                    @Override
                    public void run() {
                        player.getInventory().addItem(new ItemStack(Material.ENDER_PEARL));
                        event.getItemDrop().remove();
                    }
                });
            }
        }
    }

    @EventHandler
    public void projectileLaunchEvent(@NotNull ProjectileLaunchEvent event) {


        if (event.getEntity().getShooter() instanceof Player) {

            Player player = (Player) event.getEntity().getShooter();
            PlayerMemory memory = PlayerUtility.getPlayerMemory(player);

            if (event.getEntity().getType().equals(EntityType.ENDER_PEARL) && memory.getAbility(this) == 1) {

                int pearlsHeld = 0;

                for (ItemStack item : player.getInventory().getContents()) {
                    if (item != null) {
                        if (item.getType().equals(Material.ENDER_PEARL)) {
                            pearlsHeld = pearlsHeld + item.getAmount();
                        }
                    }
                }


                if (pearlsHeld == 1) {
                    Bukkit.getScheduler().runTask(Hourglass.getPlugin(), (Runnable) new BukkitRunnable() {
                        @Override
                        public void run() {
                            player.getInventory().addItem(new ItemStack(Material.ENDER_PEARL));
                        }
                    });
                }
            }

        }
    }
}
