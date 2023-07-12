package io.github.stonley890.hourglass.listeners;

import io.github.stonley890.hourglass.Ability;
import io.github.stonley890.hourglass.player.PlayerMemory;
import io.github.stonley890.hourglass.player.PlayerUtility;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;

public class ListenEntityTargetLivingEntity implements Listener {

    @EventHandler
    public void entityTargetLivingEntityEvent(EntityTargetLivingEntityEvent event) {

        try {
            if (event.getEntity().getType().equals(EntityType.ENDERMAN) && event.getTarget().getType().equals(EntityType.PLAYER)) {

                Player player = (Player) event.getTarget();
                PlayerMemory memory = PlayerUtility.getPlayerMemory(player);

                // Enderian's Pacifist
                if (memory.getAbility(Ability.enderianPacifist) == 1) {
                    event.setCancelled(true);
                }

            }
        } catch (NullPointerException e) {
            // Target type cannot be determined (irrelevant)
        }



    }
}
