package io.github.stonley890.hourglass.abilities;

import io.github.stonley890.hourglass.Ability;
import io.github.stonley890.hourglass.player.PlayerMemory;
import io.github.stonley890.hourglass.player.PlayerUtility;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.EnderPearl;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public class UnfathomableLuck extends Ability implements Listener {
    final String name = "Unfathomable Luck";
    final String lore = "The cruel universe has, uncharacteristically, left you unscathed by misfortune of any sort.";
    final int levels = 1;
    final BaseComponent[] details = new ComponentBuilder().append("Various factors are ").color(ChatColor.BLACK).append("much more likely").color(ChatColor.GREEN).append(" to bend in your favor.").color(ChatColor.BLACK).create();

    private static EnderPearl luckyPearl = null;

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

        if (memory.getAbility(this) == 0) removeInfiniteEffect(player, PotionEffectType.LUCK);
        else if (memory.getAbility(this) == 1) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, PotionEffect.INFINITE_DURATION, 3));
        }
    }

    @Override
    public void loop(Player player) {

    }

    public void loopTick(Player player) {
        PlayerMemory memory = PlayerUtility.getPlayerMemory(player);

        if (memory.getAbility(this) == 1) {
            Random random = new Random();
            for (Entity nearbyEntity : player.getNearbyEntities(10, 10, 10)) {
                // Change projectiles
                if (nearbyEntity instanceof Projectile projectile && !Objects.equals(projectile.getShooter(),player)) {
                    projectile.setVelocity(projectile.getVelocity().add(new Vector(random.nextDouble(-0.2, 0.2), 0, random.nextDouble(-0.2, 0.2))));
                }
            }
        }

        /*
        if (luckyPearl != null) {

            Bukkit.getLogger().info("Checking pearl");
            List<Block> blocks = new ArrayList<>();
            for (double x = luckyPearl.getLocation().getBlock().getX() - 2; x <= luckyPearl.getLocation().getBlock().getX() + 2; x++) {
                for (double y = luckyPearl.getLocation().getBlock().getY() - 2; y <= luckyPearl.getLocation().getBlock().getY() + 2; y++) {
                    for (double z = luckyPearl.getLocation().getBlock().getZ() - 2; z <= luckyPearl.getLocation().getBlock().getZ() + 2; y++) {
                        Location location = new Location(luckyPearl.getWorld(), x, y, z);
                        blocks.add(luckyPearl.getWorld().getBlockAt(location));
                    }
                }
            }

            Bukkit.getLogger().info("Got block list");
            for (Block block : blocks) {
                Bukkit.getLogger().info("Block name: " + block.getType());
                Bukkit.getLogger().info("Block passable: " + block.isPassable());
                Bukkit.getLogger().info("Breathable space: " + block.getRelative(BlockFace.UP).getRelative(BlockFace.UP).isPassable());
                if (!block.isPassable() && block.getRelative(BlockFace.UP).getRelative(BlockFace.UP).isPassable()) {
                    Bukkit.getLogger().info("All valid. Lucky pearl hit.");
                    luckyPearl.teleport(block.getLocation());
                    break;
                }
            }

        }
         */

    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player player) {
            PlayerMemory memory = PlayerUtility.getPlayerMemory(player);

            if (memory.getAbility(this) == 1) {

                boolean lucky = new Random().nextBoolean();

                // 50% chance to prevent fire tick death
                if (event.getCause().equals(EntityDamageEvent.DamageCause.FIRE_TICK) && player.getHealth() < 2 && lucky) {
                    event.setCancelled(true);
                    player.setFireTicks(0);
                    player.setHealth(0.5);
                }

                // 50% chance to survive explosion if full health
                if ((event.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_EXPLOSION) || event.getCause().equals(EntityDamageEvent.DamageCause.BLOCK_EXPLOSION)) && player.getHealth() > 19.5 && event.getDamage() > 19.5 && lucky) {
                    event.setDamage(0);
                    player.setFireTicks(0);
                    player.setHealth(0.5);
                }

            }
        }
    }

    /*
    @EventHandler
    public void onPearlThrow(@NotNull ProjectileLaunchEvent event) {

        Bukkit.getLogger().info("Projectile thrown");

        if (event.getEntity().getShooter() instanceof Player player && event.getEntity() instanceof EnderPearl enderPearl) {
            Bukkit.getLogger().info("Player/Pearl");
            PlayerMemory memory = PlayerUtility.getPlayerMemory(player);

            boolean lucky = new Random().nextBoolean();
            Bukkit.getLogger().info("Lucky throw: " + lucky);

            if (memory.getAbility(this) == 1 && lucky) {
                Bukkit.getLogger().info("Valid");
                luckyPearl = enderPearl;
            }
        }

    }
     */

}
