package io.github.stonley890.hourglass.abilities;

import io.github.stonley890.hourglass.Ability;
import io.github.stonley890.hourglass.player.PlayerMemory;
import io.github.stonley890.hourglass.player.PlayerUtility;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

public class TenderTouch extends Ability implements Listener {
    final String name = "Tender Touch";
    final String lore = "Hyper-aware of the fragility of the objects around you, you take great care in handling them and are able to interact with most items.";
    final int levels = 1;
    final BaseComponent[] details = new ComponentBuilder().append("With ").color(ChatColor.BLACK).append("any non-tool item").color(ChatColor.GREEN).append(", you can Silk Touch all blocks.").color(ChatColor.BLACK).create();
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
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        PlayerMemory memory = PlayerUtility.getPlayerMemory(player);

        if (memory.getAbility(this) == 1 && player.getInventory().getItemInMainHand().getType() == Material.AIR) {
            ItemStack silkItem = new ItemStack(Material.DIAMOND_PICKAXE, 1);
            silkItem.addEnchantment(Enchantment.SILK_TOUCH, 1);

            event.setCancelled(true);
            event.getBlock().breakNaturally(silkItem);
        }
    }
}
