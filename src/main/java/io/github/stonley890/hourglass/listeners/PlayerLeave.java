package io.github.stonley890.hourglass.listeners;

import io.github.stonley890.hourglass.player.PlayerUtility;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerLeave implements Listener {

    @EventHandler
    public void onLeave(@NotNull PlayerQuitEvent event) {
        PlayerUtility.saveToFile(event.getPlayer());
    }

}
