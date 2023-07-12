package io.github.stonley890.hourglass.listeners;

import io.github.stonley890.hourglass.Hourglass;
import io.github.stonley890.hourglass.player.PlayerMemory;
import io.github.stonley890.hourglass.player.PlayerUtility;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import static io.github.stonley890.hourglass.Ability.enderianEmbrace;
import static io.github.stonley890.hourglass.Ability.enderianPacifist;

public class ListenProjectileLaunch implements Listener {

    @EventHandler
    public void projectileLaunchEvent(ProjectileLaunchEvent event) {


        if (event.getEntity().getShooter() instanceof Player) {

            Player player = (Player) event.getEntity().getShooter();
            PlayerMemory memory = PlayerUtility.getPlayerMemory(player);

            // Enderian's Embrace
            if (event.getEntity().getType().equals(EntityType.ENDER_PEARL) && memory.getAbility(enderianEmbrace) == 1) {

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

            // Enderian's Pacifist
            if (event.getEntity().getType().equals(EntityType.ENDER_PEARL) && memory.getAbility(enderianPacifist) == 1) {
                event.setCancelled(true);
            }
        }
    }
}
