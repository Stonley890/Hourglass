package io.github.stonley890.hourglass;

import io.github.stonley890.hourglass.player.PlayerMemory;
import io.github.stonley890.hourglass.player.PlayerUtility;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.Player;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.UUID;

public class Ability {

    public String name;
    public String lore;
    public int levels;
    public BaseComponent[] details;

    public Ability(String abilityName, String abilityLore, int abilityLevels, BaseComponent[] abilityDetails) {
        name = abilityName;
        lore = abilityLore;
        levels = abilityLevels;
        details = abilityDetails;
    }

    public static int adrift = 0;
    public static int armoredWings = 1;
    public static int bound = 2;
    public static int burrowed = 3;
    public static int crepuscule = 4;
    public static int crystalSense = 5;
    public static int enderianEmbrace = 6;
    public static int enderianPacifist = 7;
    public static int extrasensory = 8;
    public static int flare = 9;
    public static int frigidFate = 10;
    public static int heatwave = 11;
    public static int hydrophobia = 12;
    public static int inflammable = 13;
    public static int minimized = 14;
    public static int nimbleDescent = 15;
    public static int phytophage = 16;
    public static int predator = 17;
    public static int swiftfeet = 18;
    public static int tenderTouch = 19;
    public static int theHourglass = 20;
    public static int unfathomableLuck = 21;


    // Adrift
    public static final PotionEffect slowFalling = new PotionEffect(PotionEffectType.SLOW_FALLING, PotionEffect.INFINITE_DURATION, 0, true, false, false);

    public static void init(Player player) {
        PlayerMemory memory = PlayerUtility.getPlayerMemory(player);

        // Adrift
        if (memory.getAbility(adrift) == 0) {
            removeInfiniteEffect(player, PotionEffectType.SLOW_FALLING);
        } else if (memory.getAbility(adrift) == 1) {
            player.addPotionEffect(slowFalling);
        } else if (memory.getAbility(adrift) == 2) {
            if (player.isGliding()) {player.addPotionEffect(slowFalling);}
            else {removeInfiniteEffect(player, PotionEffectType.SLOW_FALLING);}
        }

        // Armored Wings
        if (memory.getAbility(armoredWings) == 0 && player.getInventory().getChestplate() != null) {
            player.getInventory().getChestplate().getItemMeta().removeAttributeModifier(Attribute.GENERIC_ARMOR);
        } else if (memory.getAbility(armoredWings) == 1) {
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
                } else if (player.getUniqueId().equals(UUID.fromString("b93bf0c1-40f4-4e6c-8a99422f95bc019e"))) {
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

        // Crepuscule
        if (memory.getAbility(crepuscule) == 0) {
            removeInfiniteEffect(player, PotionEffectType.NIGHT_VISION);
        } else if (memory.getAbility(crepuscule) == 1) {
            player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, PotionEffect.INFINITE_DURATION, 0, true, false, false));
        }

        // Enderian's Embrace
        if (memory.getAbility(enderianEmbrace) == 1) {
            if (!player.getInventory().contains(Material.ENDER_PEARL)) {
                player.getInventory().addItem(new ItemStack(Material.ENDER_PEARL, 1));
            }
        }
    }

    public static void removeInfiniteEffect(Player player, PotionEffectType effectType) {
        for (PotionEffect effect : player.getActivePotionEffects()) {
            if (effect.getType().equals(effectType) && effect.isInfinite()) {
                player.removePotionEffect(effectType);
                break;
            }
        }
    }

}
