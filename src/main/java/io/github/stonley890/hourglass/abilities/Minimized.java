package io.github.stonley890.hourglass.abilities;

import io.github.stonley890.hourglass.Ability;
import io.github.stonley890.hourglass.player.PlayerMemory;
import io.github.stonley890.hourglass.player.PlayerUtility;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

public class Minimized extends Ability {
    final String name = "Minimized";
    final String lore = "You find that it often doesn’t take much to hurt you.";
    final int levels = 2;
    final BaseComponent[] details = new ComponentBuilder().append("Your maximum health is reduced to ").color(ChatColor.BLACK).append("8").color(ChatColor.GREEN).append(" / ").color(ChatColor.BLACK).append("6").color(ChatColor.BLUE).append(" hearts.").color(ChatColor.BLACK).create();
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
        PlayerMemory memory = PlayerUtility.getPlayerMemory(player);

        if (memory.getAbility(this) == 0) player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
        else if (memory.getAbility(this) == 1) player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(8*2);
        else if (memory.getAbility(this) == 2) player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(6*2);

    }

    @Override
    public void loop(Player player) {

    }
}
