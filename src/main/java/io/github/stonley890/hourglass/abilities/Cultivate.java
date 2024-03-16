package io.github.stonley890.hourglass.abilities;

import io.github.stonley890.hourglass.Ability;
import io.github.stonley890.hourglass.player.PlayerMemory;
import io.github.stonley890.hourglass.player.PlayerUtility;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.FluidCollisionMode;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;

public class Cultivate extends Ability {
    final String name = "Cultivate";
    final String lore = "Your mysterious resonance with the natural world allows you to accelerate plant growth.";
    final int levels = 1;
    final BaseComponent[] details = new ComponentBuilder().append("While sneaking, the plant you are looking at will grow ").color(ChatColor.BLACK).append("1").color(ChatColor.GREEN).append(" stage per second and the cost of ").append("4").color(ChatColor.GREEN).append(" hunger points per block.").color(ChatColor.BLACK).create();
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
        PlayerMemory memory = PlayerUtility.getPlayerMemory(player);

        if (memory.getAbility(this) == 1) {
            if (player.isSneaking()) {

                Block targetBlock = player.getTargetBlockExact(3, FluidCollisionMode.NEVER);

                if (targetBlock != null && player.getFoodLevel() > 3 && targetBlock.applyBoneMeal(BlockFace.DOWN)) player.setFoodLevel(player.getFoodLevel() - 4);
            }
        }


    }
}
