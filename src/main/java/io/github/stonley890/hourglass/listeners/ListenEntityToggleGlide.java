package io.github.stonley890.hourglass.listeners;

import io.github.stonley890.hourglass.Ability;
import io.github.stonley890.hourglass.player.PlayerMemory;
import io.github.stonley890.hourglass.player.PlayerUtility;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityToggleGlideEvent;
import org.bukkit.potion.PotionEffectType;

import static io.github.stonley890.hourglass.Ability.adrift;
import static io.github.stonley890.hourglass.Ability.slowFalling;

public class ListenEntityToggleGlide implements Listener {

    @EventHandler
    public void entityToggleGlideEvent (EntityToggleGlideEvent event) {
        Player player = (Player) event.getEntity();
        PlayerMemory memory = PlayerUtility.getPlayerMemory(player);

        // Adrift
        if (memory.getAbility(adrift) == 2 && !player.isGliding() && !player.isSneaking()) {

            player.addPotionEffect(slowFalling);

        } else if (memory.getAbility(adrift) == 2 && player.isGliding()) {

            Ability.removeInfiniteEffect(player, PotionEffectType.SLOW_FALLING);
        }
    }
}
