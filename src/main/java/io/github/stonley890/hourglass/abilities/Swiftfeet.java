package io.github.stonley890.hourglass.abilities;

import io.github.stonley890.hourglass.Ability;
import io.github.stonley890.hourglass.Hourglass;
import io.github.stonley890.hourglass.player.PlayerMemory;
import io.github.stonley890.hourglass.player.PlayerUtility;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

public class Swiftfeet extends Ability {
    final String name = "Swiftfeet";
    final String lore = "As quick as the gusting wind, you stride great lengths in little time.";
    final int levels = 2;
    final BaseComponent[] details = new ComponentBuilder().append("You have a ").color(ChatColor.BLACK).append("15%").color(ChatColor.GREEN).append(" / ").color(ChatColor.BLACK).append("25%").color(ChatColor.BLUE).append(" speed bonus.").color(ChatColor.BLACK).create();
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

        if (memory.getAbility(this) == 0) player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(Hourglass.defaultMovementSpeed);
        else if (memory.getAbility(this) == 1) player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(Hourglass.defaultMovementSpeed*1.15);
        else if (memory.getAbility(this) == 2) player.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(Hourglass.defaultMovementSpeed*1.25);
    }

    @Override
    public void loop(Player player) {

    }
}
