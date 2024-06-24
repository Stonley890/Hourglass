package io.github.stonley890.hourglass.listeners;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.jetbrains.annotations.NotNull;

public class ListenPlayerUse implements Listener {

    @EventHandler
    public void onPlayerUse(@NotNull PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (event.getAction().equals(Action.RIGHT_CLICK_AIR) && event.getMaterial().equals(Material.FIREWORK_ROCKET))
            event.setCancelled(true);
    }

}
