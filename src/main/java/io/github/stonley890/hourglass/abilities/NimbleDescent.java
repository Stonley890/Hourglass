package io.github.stonley890.hourglass.abilities;

import io.github.stonley890.hourglass.Ability;
import io.github.stonley890.hourglass.player.PlayerMemory;
import io.github.stonley890.hourglass.player.PlayerUtility;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class NimbleDescent extends Ability implements Listener {
    final String name = "Nimble Descent";
    final String lore = "With elegance, you can recover much quicker from great falls.";
    final int levels = 2;
    final BaseComponent[] details = new ComponentBuilder("You take ").append("15").color(ChatColor.GREEN).append(" / ").color(ChatColor.BLACK).append("25").color(ChatColor.BLUE).append(" % less fall damage.").color(ChatColor.BLACK).create();
    @Override
    public String getName() {
        return name;
    }
    @Override
    public String getLore() {
        return lore;
    }
    @Override
    public int getLevels() {
        return levels;
    }
    @Override
    public BaseComponent[] getDetails() {
        return details;
    }

    @Override
    public void init(Player player) {

    }

    @Override
    public void loop(Player player) {

    }

    @EventHandler
    public void onFall(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player player) {
            PlayerMemory memory = PlayerUtility.getPlayerMemory(player);
            if (memory.getAbility(this) > 0 && event.getCause().equals(EntityDamageEvent.DamageCause.FALL)) {
                if (memory.getAbility(this) == 1) event.setDamage(event.getDamage() - (event.getDamage()*0.15));
                else if (memory.getAbility(this) == 2) event.setDamage(event.getDamage() - (event.getDamage()*0.25));
            }
        }
    }
}
