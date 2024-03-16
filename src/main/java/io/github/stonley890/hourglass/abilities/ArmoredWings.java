package io.github.stonley890.hourglass.abilities;

import io.github.stonley890.hourglass.Ability;
import io.github.stonley890.hourglass.player.PlayerMemory;
import io.github.stonley890.hourglass.player.PlayerUtility;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class ArmoredWings extends Ability implements Listener {

    final String name = "Armored Wings";
    final String lore = "Strengthened from use, your wings alone grant you passable protection from damage.";
    final int levels = 2;
    final BaseComponent[] details = new ComponentBuilder().append("You are given a binding unbreakable elytra with ").color(ChatColor.BLACK).append("2").color(ChatColor.GREEN).append(" armor points of protection.").color(ChatColor.BLACK).create();
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
    public ArmoredWings() {}

    @Override
    public void init(Player player) {
        PlayerMemory memory = PlayerUtility.getPlayerMemory(player);

        if (memory.getAbility(this) == 0 && player.getInventory().getChestplate() != null) {
            player.getInventory().getChestplate().getItemMeta().removeAttributeModifier(Attribute.GENERIC_ARMOR);
        } else if (memory.getAbility(this) == 1) {
            if (player.getInventory().getChestplate() == null) {
                ItemStack armoredElytra = new ItemStack(Material.ELYTRA);
                ItemMeta elytraMeta = armoredElytra.getItemMeta();
                elytraMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "ArmoredWings", 2, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST));

                // Custom Elytra
                if (player.getUniqueId().equals(UUID.fromString("eedf3c55-2e73-4e73-99a7-81d953745f0a"))) {
                    elytraMeta.setDisplayName("Khylen\'s Wings");
                    elytraMeta.setCustomModelData(1009);
                } else if (player.getUniqueId().equals(UUID.fromString("29313427-e739-4b45-af85-0b3a485df978"))) {
                    elytraMeta.setDisplayName("Zara\'s Wings");
                    elytraMeta.setCustomModelData(1007);
                } else if (player.getUniqueId().equals(UUID.fromString("0d005102-db98-494c-8a2a-029f7810eb67"))) {
                    elytraMeta.setDisplayName("Argentum\'s Wings");
                    elytraMeta.setCustomModelData(1006);
                } else if (player.getUniqueId().equals(UUID.fromString("0745f7d3-3a45-4f85-86c4-ab0e7b7cdda5"))) {
                    elytraMeta.setDisplayName("Nyra\'s Wings");
                    elytraMeta.setCustomModelData(1005);
                } else if (player.getUniqueId().equals(UUID.fromString("b93bf0c1-40f4-4e6c-8a99-422f95bc019e"))) {
                    elytraMeta.setDisplayName("Li\'s Wings");
                    elytraMeta.setCustomModelData(1004);
                } else if (player.getUniqueId().equals(UUID.fromString("76db2bdb-0db0-455f-9be0-fee858fc16f9"))) {
                    elytraMeta.setDisplayName("Heron\'s Wings");
                    elytraMeta.setCustomModelData(1003);
                } else if (player.getUniqueId().equals(UUID.fromString("05f7aafb-9b83-4b34-9268-3f4f7095fb56"))) {
                    elytraMeta.setDisplayName("Fyndur\'s Wings");
                    elytraMeta.setCustomModelData(1002);
                } else if (player.getUniqueId().equals(UUID.fromString("05f7aafb-9b83-4b34-9268-3f4f7095fb56"))) {
                    elytraMeta.setDisplayName("Ash\'s Wings");
                    elytraMeta.setCustomModelData(1000);
                } else if (player.getUniqueId().equals(UUID.fromString("fcefa857-32fe-4067-8c94-aadd44400ecb"))) {
                    elytraMeta.setDisplayName("Nimbus\'s Wings");
                    elytraMeta.setCustomModelData(1001);
                }

                // Set unbreakable
                elytraMeta.setUnbreakable(true);

                armoredElytra.setItemMeta(elytraMeta);

                // Clear item if already exists
                player.getInventory().removeItem(armoredElytra);

                // Set chestplate
                player.getInventory().setChestplate(armoredElytra);
            }
        }
    }

    @Override
    public void loop(Player player) {

    }

    @EventHandler
    public void playerDeathEvent(@NotNull PlayerDeathEvent event) {
        List<ItemStack> items = event.getDrops();
        if (!items.isEmpty()) {
            items.removeIf(item -> item.getType().equals(Material.ELYTRA) && Objects.requireNonNull(item.getItemMeta()).isUnbreakable());
        }
    }

}
