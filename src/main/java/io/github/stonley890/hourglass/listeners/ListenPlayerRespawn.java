package io.github.stonley890.hourglass.listeners;

import io.github.stonley890.hourglass.Ability;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.jetbrains.annotations.NotNull;

public class ListenPlayerRespawn implements Listener {

    @EventHandler
    public void playerRespawnEvent(@NotNull PlayerRespawnEvent event) {
        // Reload all abilities
        Ability.reload(event.getPlayer());
    }
}
