package io.github.stonley890.hourglass.listeners;

import io.github.stonley890.hourglass.player.PlayerMemory;
import io.github.stonley890.hourglass.player.PlayerUtility;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import static io.github.stonley890.hourglass.Ability.*;

public class ListenPlayerToggleSneak implements Listener {


    @EventHandler
    public void playerToggleSneakEvent(PlayerToggleSneakEvent event) {

        Player player = event.getPlayer();
        PlayerMemory memory = PlayerUtility.getPlayerMemory(player);

        if (event.isSneaking()) {

            // Adrift
            if /* Player has Adrift 1/2*/ (memory.getAbility(adrift) > 0) {
                removeInfiniteEffect(player, PotionEffectType.SLOW_FALLING);
            }

            // Bound
            if (memory.getAbility(bound) == 1) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, PotionEffect.INFINITE_DURATION, 0, true, false, true));
            } else if (memory.getAbility(bound) == 2) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, PotionEffect.INFINITE_DURATION, 1, true, false, true));
            } else if (memory.getAbility(bound) == 3) {
                player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, PotionEffect.INFINITE_DURATION, 5, true, false, true));
            }

        } else {

            // Adrift
            if /* (Player has Adrift 1) OR (Player has Adrift 2 AND Player is gliding) */ (memory.getAbility(adrift) == 1 || (memory.getAbility(adrift) == 2 && player.isGliding())) {
                player.addPotionEffect(slowFalling);
            }

            // Bound
            if (memory.getAbility(bound) > 0) {
                removeInfiniteEffect(player, PotionEffectType.JUMP);
            }
        }
    }
}
