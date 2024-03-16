package io.github.stonley890.hourglass;

import io.github.stonley890.hourglass.commands.Abilities;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public abstract class Ability {

    protected String name = "null";
    protected String lore = "null";
    protected int levels = 0;
    protected BaseComponent[] details = new ComponentBuilder("null").create();

    public Ability() {}

    public abstract void init(Player player);
    public abstract void loop(Player player);
    public abstract String getName();
    public abstract String getLore();
    public abstract int getLevels();
    public abstract BaseComponent[] getDetails();

    public static void reload(Player player) {
        for (Ability ability : Abilities.abilities) {
            ability.init(player);
        }
    }

    public static void loopTask() {
        for (Ability ability : Abilities.abilities) {
            for (Player player : Bukkit.getOnlinePlayers())
                ability.loop(player);
        }
    }

    public static void removeInfiniteEffect(@NotNull Player player, PotionEffectType effectType) {
        for (PotionEffect effect : player.getActivePotionEffects()) {
            if (effect.getType().equals(effectType) && effect.isInfinite()) {
                player.removePotionEffect(effectType);
                break;
            }
        }
    }

}
