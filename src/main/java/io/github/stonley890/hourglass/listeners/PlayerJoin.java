package io.github.stonley890.hourglass.listeners;

import io.github.stonley890.hourglass.player.PlayerUtility;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.jetbrains.annotations.NotNull;

public class PlayerJoin implements Listener {
    @EventHandler
    public void onJoin(@NotNull PlayerJoinEvent event) {

        PlayerUtility.fetchPlayerMemory(event.getPlayer());

    }

}
