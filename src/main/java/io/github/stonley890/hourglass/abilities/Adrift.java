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
import org.bukkit.event.entity.EntityToggleGlideEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

public class Adrift extends Ability implements Listener {

    final String name = "Adrift";
    final String lore = "With immense effort, you are able to stay suspended in the air, descending slowly towards the ground.";
    final int levels = 2;
    final BaseComponent[] details = new ComponentBuilder().append("You have Slow Falling ").color(ChatColor.BLACK).append("indefinitely").color(ChatColor.GREEN).append(" / ").color(ChatColor.BLACK).append("while using elytra").color(ChatColor.BLUE).append(". Sneaking will disable the effect.").color(ChatColor.BLACK).create();
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
    public Adrift() {}

    @Override
    public void init(Player player) {
        PlayerMemory memory = PlayerUtility.getPlayerMemory(player);

        if (memory.getAbility(this) == 0) {
            removeInfiniteEffect(player, PotionEffectType.SLOW_FALLING);
        } else if (memory.getAbility(this) == 1) {
            player.addPotionEffect(slowFalling);
        } else if (memory.getAbility(this) == 2) {
            if (player.isGliding()) {player.addPotionEffect(slowFalling);}
            else {removeInfiniteEffect(player, PotionEffectType.SLOW_FALLING);}
        }
    }

    @Override
    public void loop(Player player) {

    }



    @EventHandler
    public void entityToggleGlideEvent (@NotNull EntityToggleGlideEvent event) {
        Player player = (Player) event.getEntity();
        PlayerMemory memory = PlayerUtility.getPlayerMemory(player);

        if (memory.getAbility(this) == 2 && !player.isGliding() && !player.isSneaking()) {

            player.addPotionEffect(Adrift.slowFalling);

        } else if (memory.getAbility(this) == 2 && player.isGliding()) {

            Ability.removeInfiniteEffect(player, PotionEffectType.SLOW_FALLING);
        }
    }

    @EventHandler
    public void playerToggleSneakEvent(@NotNull PlayerToggleSneakEvent event) {

        Player player = event.getPlayer();
        PlayerMemory memory = PlayerUtility.getPlayerMemory(player);

        if (event.isSneaking()) {

            if /* Player has Adrift 1/2*/ (memory.getAbility(this) > 0) {
                removeInfiniteEffect(player, PotionEffectType.SLOW_FALLING);
            }

        } else {

            if /* (Player has Adrift 1) OR (Player has Adrift 2 AND Player is gliding) */ (memory.getAbility(this) == 1 || (memory.getAbility(this) == 2 && player.isGliding())) {
                player.addPotionEffect(Adrift.slowFalling);
            }
        }
    }

    public static final PotionEffect slowFalling = new PotionEffect(PotionEffectType.SLOW_FALLING, PotionEffect.INFINITE_DURATION, 0, true, false, false);
}
