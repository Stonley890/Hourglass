package io.github.stonley890.hourglass.listeners;

import io.github.stonley890.hourglass.Ability;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

public class ListenPlayerRespawn implements Listener {

    @EventHandler
    public void playerRespawnEvent(PlayerRespawnEvent event) {
        // Init all abilities
        Ability.init(event.getPlayer());


    }
}
