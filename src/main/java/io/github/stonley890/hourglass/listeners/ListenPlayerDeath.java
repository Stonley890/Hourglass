package io.github.stonley890.hourglass.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

public class ListenPlayerDeath implements Listener {

    @EventHandler
    public void playerDeathEvent(PlayerDeathEvent event) {
        Player player = (Player) event.getEntity();

        // Armored Wings
        // Remove unbreakable elytra
        if (!event.getDrops().isEmpty()) {
            for (ItemStack item : event.getDrops()) {
                if (item.getType().equals(Material.ELYTRA) && item.getItemMeta().isUnbreakable()) {
                    event.getDrops().remove(item);
                }
            }
        }
    }
}
