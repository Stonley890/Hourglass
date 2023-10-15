package io.github.stonley890.hourglass;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import com.comphenix.protocol.wrappers.WrappedDataValue;
import com.comphenix.protocol.wrappers.WrappedDataWatcher;
import com.comphenix.protocol.wrappers.WrappedWatchableObject;
import io.github.stonley890.hourglass.commands.Abilities;
import io.github.stonley890.hourglass.listeners.*;
import io.github.stonley890.hourglass.player.PlayerMemory;
import io.github.stonley890.hourglass.player.PlayerUtility;
import io.github.stonley890.hourglass.protocol.EntityData;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.minecraft.network.protocol.Packet;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Sheep;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

import static io.github.stonley890.hourglass.Ability.*;

public final class Hourglass extends JavaPlugin {

    static Hourglass plugin;

    private ProtocolManager protocolManager;
    Set<Integer> glowIds = new HashSet<>();

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        protocolManager = ProtocolLibrary.getProtocolManager();

        // Commands
        Objects.requireNonNull(getCommand("abilities")).setExecutor(new Abilities());

        // Listeners
        getServer().getPluginManager().registerEvents(new ListenEntityToggleGlide(), this);
        getServer().getPluginManager().registerEvents(new ListenPlayerToggleSneak(), this);
        getServer().getPluginManager().registerEvents(new ListenPlayerDeath(), this);
        getServer().getPluginManager().registerEvents(new ListenPlayerRespawn(), this);
        getServer().getPluginManager().registerEvents(new ListenProjectileLaunch(), this);
        getServer().getPluginManager().registerEvents(new ListenPlayerDropItem(), this);
        getServer().getPluginManager().registerEvents(new ListenEntityTargetLivingEntity(), this);

        // Create list of abilities
        Abilities.abilities.add(new Ability(
                "Adrift",
                "With immense effort, you are able to stay suspended in the air, descending slowly towards the ground.",
                2,
                new ComponentBuilder().append("You have Slow Falling ").color(ChatColor.BLACK).append("indefinitely").color(ChatColor.GREEN).append(" / ").color(ChatColor.BLACK).append("while using elytra").color(ChatColor.BLUE).append(".").color(ChatColor.BLACK).create())
        );
        Abilities.abilities.add(new Ability(
                "Armored Wings",
                "Strengthened from use, your wings alone grant you passable protection from damage.",
                1,
                new ComponentBuilder().append("You are given unbreakable elytra with ").color(ChatColor.BLACK).append("2").color(ChatColor.GREEN).append(" armor points of protection.").color(ChatColor.BLACK).create())
        );
        Abilities.abilities.add(new Ability(
                "Bound",
                "With great strength, you are able to leap great heights with little effort.",
                3,
                new ComponentBuilder().append("You have togglable Jump Boost ").color(ChatColor.BLACK).append("1").color(ChatColor.GREEN).append(" / ").color(ChatColor.BLACK).append("2").color(ChatColor.BLUE).append(" / ").color(ChatColor.BLACK).append("6").color(ChatColor.DARK_PURPLE).append(".").color(ChatColor.BLACK).create())
        );
        Abilities.abilities.add(new Ability(
                "Burrowed",
                "Weakened by the surface world, your habitat of refuge remains the only solace you know.",
                1,
                new ComponentBuilder().append("While exposed to the sky, you have Weakness ").color(ChatColor.BLACK).append("1").color(ChatColor.GREEN).append(".").color(ChatColor.BLACK).create())
        );
        Abilities.abilities.add(new Ability(
                "Crepuscule",
                "Your senses are focused for the late hours of dusk, where peril may lie at any distance.",
                1,
                new ComponentBuilder().append("While in the dark, you have Night Vision ").color(ChatColor.BLACK).append("1").color(ChatColor.GREEN).append(".").color(ChatColor.BLACK).create())
        );
        Abilities.abilities.add(new Ability(
                "Crystal Sense",
                "The mysterious power of End Crystals grants you regenerative properties.",
                1,
                new ComponentBuilder().append("End Crystals within 24 m grant you ").color(ChatColor.BLACK).append("Regeneration 1").color(ChatColor.GREEN).append(" and ").color(ChatColor.BLACK).append("Glowing").color(ChatColor.GREEN).append(". The effect lingers for 6 seconds upon leaving.").color(ChatColor.BLACK).create())
        );
        Abilities.abilities.add(new Ability(
                "Enderian’s Embrace",
                "You rest well with your Enderian traits, capable of calling on your abilities at will.",
                1,
                new ComponentBuilder().append("You have access to ").color(ChatColor.BLACK).append("infinite ender pearls").color(ChatColor.GREEN).append(". This overrides Enderian’s Pacifist.").color(ChatColor.BLACK).create())
        );
        Abilities.abilities.add(new Ability(
                "Enderian’s Pacifist",
                "A true ally to the Enderian ways, you find yourself unwilling — or unable? — to utilize their fragments for personal gain.",
                1,
                new ComponentBuilder().append("You are unable to teleport using ender pearls. Chorus fruit teleportation is limited to only 1 block.").color(ChatColor.BLACK).create())
        );
        Abilities.abilities.add(new Ability(
                "Extrasensory",
                "Tuned to the presence of those around you, your powerful senses allow you to perceive hidden threats.",
                1,
                new ComponentBuilder().append("You are able to highlight enemies up to ").color(ChatColor.BLACK).append("20").color(ChatColor.GREEN).append(" meters away when sneaking.").color(ChatColor.BLACK).create())
        );
        Abilities.abilities.add(new Ability(
                "Flare",
                "As you enter more and more danger, you feel the intense yet rejuvenating burn of fury ignite in your heart.",
                1,
                new ComponentBuilder().append("While at low health, you gain ").color(ChatColor.BLACK).append("Regeneration 1").color(ChatColor.GREEN).append(" and ").color(ChatColor.BLACK).append("Fire Resistance").color(ChatColor.GREEN).append(" for 10 s every 20 s.").color(ChatColor.BLACK).create())
        );
        Abilities.abilities.add(new Ability(
                "Frigid Fate",
                "As the cold envelopes you, you rapidly begin to fall victim to its effects.",
                2,
                new ComponentBuilder().append("While in cold biomes, you gain Weakness and Slowness ").color(ChatColor.BLACK).append("1").color(ChatColor.GREEN).append("/").color(ChatColor.BLACK).append("2").color(ChatColor.BLUE).append(".").color(ChatColor.BLACK).create())
        );
        Abilities.abilities.add(new Ability(
                "Heatwave",
                "Exposure to heat hinders you to the point of exhaustion… or worse.",
                2,
                new ComponentBuilder().append("While in hot biomes, you gain Weakness and Slowness ").color(ChatColor.BLACK).append("1").color(ChatColor.GREEN).append(" / ").color(ChatColor.BLACK).append("2").color(ChatColor.BLUE).append(".").color(ChatColor.BLACK).create())
        );
        Abilities.abilities.add(new Ability(
                "Hydrophobia",
                "The awful sting of water leaves you scarred… but not nearly as much as your fear of it.",
                2,
                new ComponentBuilder().append("While in rain or in water, you take ").color(ChatColor.BLACK).append("1").color(ChatColor.GREEN).append("/").color(ChatColor.BLACK).append("2").color(ChatColor.BLUE).append(" damage. ").color(ChatColor.BLACK).append("Rain damage is negated by headgear.").color(ChatColor.GREEN).create())
        );
        Abilities.abilities.add(new Ability(
                "Inflammable",
                "Conflagrations start with even the smallest of sparks.",
                1,
                new ComponentBuilder("You are unable to be burned.").color(ChatColor.BLACK).create()
        ));
        Abilities.abilities.add(new Ability(
                "Minimized",
                "With a smaller stature, you find that it often doesn’t take much to hurt you.",
                2,
                new ComponentBuilder("NULL").create())
        );
        Abilities.abilities.add(new Ability(
                "Nimble Descent",
                "With elegance, you can recover much quicker from great falls.",
                2,
                new ComponentBuilder("You take ").append("15").color(ChatColor.GREEN).append(" / ").color(ChatColor.BLACK).append("25").color(ChatColor.BLUE).append(" % less fall damage.").color(ChatColor.BLACK).create())
        );
        Abilities.abilities.add(new Ability(
                "Phytophage",
                "You find that, upon eating meat, you are left weaker than before.",
                1,
                new ComponentBuilder().append("You suffer from ").color(ChatColor.BLACK).append("Nausea, Hunger, and Weakness").color(ChatColor.GREEN).append(" for 12 s when eating meat. You gain ").color(ChatColor.BLACK).append("Saturation 2").color(ChatColor.GREEN).append(" when eating non-meat foods.").color(ChatColor.BLACK).create())
        );
        Abilities.abilities.add(new Ability(
                "Predator",
                "The thrill of the hunt and the reward offered are too great to give up, even for one meal.",
                1,
                new ComponentBuilder().append("You suffer from ").color(ChatColor.BLACK).append("Nausea, Hunger, and Slowness").color(ChatColor.GREEN).append(" for 16 s when eating non-meat foods. You gain ").color(ChatColor.BLACK).append("Saturation").color(ChatColor.GREEN).append(" when eating meat.").color(ChatColor.BLACK).create())
        );
        Abilities.abilities.add(new Ability(
                "Swiftfeet",
                "As quick as the gusting wind, you stride great lengths in little time.",
                2,
                new ComponentBuilder().append("You have a ").color(ChatColor.BLACK).append("15%").color(ChatColor.GREEN).append(" / ").color(ChatColor.BLACK).append("25%").color(ChatColor.BLUE).append(" speed bonus.").color(ChatColor.BLACK).create())
        );
        Abilities.abilities.add(new Ability(
                "Tender Touch",
                "Hyperware of the fragility of the objects around you, you take great care in handling them and are able to interact with most items.",
                1,
                new ComponentBuilder().append("With ").color(ChatColor.BLACK).append("any non-tool item").color(ChatColor.GREEN).append(", you can Silk Touch all blocks.").color(ChatColor.BLACK).create())
        );
        Abilities.abilities.add(new Ability(
                "The Hourglass",
                "What is this…?",
                1,
                new ComponentBuilder().append("You are given an ").color(ChatColor.BLACK).append("Ancient Hourglass").italic(true).append(" that, upon it leaving your inventory, will kill you. Upon reaching low health, you will be given ").italic(false).append("Instant Health and Regeneration").color(ChatColor.GREEN).append(" for 10 seconds. This only activates once per life.").color(ChatColor.BLACK).create())
        );
        Abilities.abilities.add(new Ability(
                "Unfathomable Luck",
                "The cruel universe has, uncharacteristically, left you unscathed by misfortune of any sort.",
                1,
                new ComponentBuilder().append("Various factors are ").color(ChatColor.BLACK).append("much more likely").color(ChatColor.GREEN).append(" to bend in your favor.").color(ChatColor.BLACK).create())
        );

        List<Ability> abilities = new ArrayList<Ability>();


        // Burrowed
        Runnable repeatTask = new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : getServer().getOnlinePlayers()) {
                    PlayerMemory memory = PlayerUtility.getPlayerMemory(player);

                    // Burrowed
                    if (memory.getAbility(burrowed) == 1) {
                        // If skylight level is 15, apply weakness. Otherwise, remove
                        if (player.getLocation().getBlock().getLightFromSky() == 15) {
                            player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, PotionEffect.INFINITE_DURATION, 0, true, false, true));
                        } else {
                            removeInfiniteEffect(player, PotionEffectType.WEAKNESS);
                        }
                    } else if (memory.getAbility(burrowed) == 0) {
                        removeInfiniteEffect(player, PotionEffectType.WEAKNESS);
                    }

                    // Crystal Sense
                    if (memory.getAbility(crystalSense) == 1) {
                        for (Entity entity : player.getNearbyEntities(24, 24, 24)) {
                            if (entity.getType().equals(EntityType.ENDER_CRYSTAL)) {
                                player.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 120, 0, true, false, true));
                                player.addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 120, 0, true, false, true));

                                break;
                            }
                        }
                    }

                    // Extrasensory
                    if (memory.getAbility(extrasensory) == 1) {
                        // glowIds.clear();
                        if (player.isSneaking()) {
                            for (Entity entity : Bukkit.selectEntities(player, "@e[distance=0..20]")) {

                                entity.setCustomName(String.valueOf(new Random().nextInt()));
                                // glowIds.add(entity.getEntityId());

                                PacketContainer packet = ProtocolLibrary.getProtocolManager().createPacket(PacketType.Play.Server.ENTITY_METADATA);

                                final WrappedDataWatcher dataWatcher = WrappedDataWatcher.getEntityWatcher(entity).deepClone();
                                dataWatcher.setEntity(entity);
                                packet.getWatchableCollectionModifier().write(0, dataWatcher.getWatchableObjects());

                                final List<WrappedWatchableObject> metadata = packet.getWatchableCollectionModifier().read(0); //dataWatcher.getWatchableObjects();
                                WrappedWatchableObject bitMaskContainer = metadata.stream().filter(obj -> obj.getIndex() == 0).findAny().orElse(null);
                                if (bitMaskContainer == null) return;
                                byte bitMask = (byte) bitMaskContainer.getValue();

                                Bukkit.getLogger().info("INITIAL: " + bitMask);
                                Bukkit.getLogger().info("GLOWING: " + EntityData.GLOWING.getBitMask());
                                Bukkit.getLogger().info("FINAL: " + (bitMask | EntityData.GLOWING.getBitMask()));
                                bitMask = EntityData.GLOWING.setBit(bitMask);

                                bitMaskContainer.setValue(bitMask);

                                metadata.set(0, bitMaskContainer);

                                packet.getWatchableCollectionModifier().write(0, metadata);

                                /*
                                try {

                                    final List<WrappedDataValue> wrappedDataValueList = new ArrayList<>();

                                    for (final WrappedWatchableObject entry : dataWatcher.getWatchableObjects()) {
                                        if (entry == null) continue;

                                        final WrappedDataWatcher.WrappedDataWatcherObject watcherObject = entry.getWatcherObject();
                                        wrappedDataValueList.add(
                                                new WrappedDataValue(
                                                        watcherObject.getIndex(),
                                                        watcherObject.getSerializer(),
                                                        entry.getRawValue()
                                                )
                                        );
                                    }

                                    packet.getDataValueCollectionModifier().write(0, wrappedDataValueList);

                                } catch (Exception e) {
                                    e.printStackTrace();
                                    packet.getWatchableCollectionModifier().write(0, dataWatcher.getWatchableObjects());
                                }
                                 */


                                ProtocolLibrary.getProtocolManager().sendServerPacket(player, packet);

                            }
                        } else {
                            /*
                            for (Entity entity : Bukkit.selectEntities(player, "@e[distance=0..]")) {
                                entity.setCustomName(String.valueOf(new Random().nextInt()));

                                PacketContainer packet = ProtocolLibrary.getProtocolManager().createPacket(PacketType.Play.Server.ENTITY_METADATA);
                                final WrappedDataWatcher dataWatcher = WrappedDataWatcher.getEntityWatcher(entity).deepClone();
                                dataWatcher.setEntity(entity);
                                final List<WrappedWatchableObject> metadata = dataWatcher.getWatchableObjects();
                                WrappedWatchableObject bitMaskContainer = metadata.stream().filter(obj -> obj.getIndex() == 0).findAny().orElse(null);
                                if (bitMaskContainer == null) return;
                                byte bitMask = (byte) bitMaskContainer.getValue();

                                bitMask = EntityData.GLOWING.unsetBit(bitMask);

                                bitMaskContainer.setValue(bitMask);

                                try {

                                    final List<WrappedDataValue> wrappedDataValueList = new ArrayList<>();

                                    for (final WrappedWatchableObject entry : dataWatcher.getWatchableObjects()) {
                                        if (entry == null) continue;

                                        final WrappedDataWatcher.WrappedDataWatcherObject watcherObject = entry.getWatcherObject();
                                        wrappedDataValueList.add(
                                                new WrappedDataValue(
                                                        watcherObject.getIndex(),
                                                        watcherObject.getSerializer(),
                                                        entry.getRawValue()
                                                )
                                        );
                                    }

                                    packet.getDataValueCollectionModifier().write(0, wrappedDataValueList);

                                } catch (Exception e) {
                                    e.printStackTrace();
                                    packet.getWatchableCollectionModifier().write(0, dataWatcher.getWatchableObjects());
                                }

                                ProtocolLibrary.getProtocolManager().sendServerPacket(player, packet);
                            }
                            */
                        }


                    }

                }
            }
        };

        // Run repeat task once per second
        Bukkit.getScheduler().runTaskTimer(this, repeatTask, 1, 20);

        /*
        ProtocolLibrary.getProtocolManager().addPacketListener(new PacketAdapter(this, PacketType.Play.Server.ENTITY_METADATA) {
            @Override
            public void onPacketSending(PacketEvent event) {

                if(event.isCancelled()) return;
                if(event.getPacketType() != PacketType.Play.Server.ENTITY_METADATA) return;


                final PacketContainer packet = event.getPacket();
                final Entity entity = packet.getEntityModifier(event).read(0);

                if(entity == null) return;

                final WrappedDataWatcher dataWatcher =
                        WrappedDataWatcher.getEntityWatcher(entity).deepClone();

                final List<WrappedWatchableObject> metadata = dataWatcher.getWatchableObjects();
                WrappedWatchableObject bitMaskContainer = metadata.stream().filter(obj -> obj.getIndex() == 0).findAny().orElse(null);
                if (bitMaskContainer == null) return;
                byte bitMask = (byte) bitMaskContainer.getValue();

                if (PlayerUtility.getPlayerMemory(event.getPlayer()).getAbility(extrasensory) > 0 && glowIds.contains(entity.getEntityId())) {
                    bitMask = EntityData.GLOWING.setBit(bitMask);
                }

                bitMaskContainer.setValue(bitMask);

                try {

                    final List<WrappedDataValue> wrappedDataValueList = new ArrayList<>();

                    for (final WrappedWatchableObject entry : dataWatcher.getWatchableObjects()) {
                        if (entry == null) continue;

                        final WrappedDataWatcher.WrappedDataWatcherObject watcherObject = entry.getWatcherObject();
                        wrappedDataValueList.add(
                                new WrappedDataValue(
                                        watcherObject.getIndex(),
                                        watcherObject.getSerializer(),
                                        entry.getRawValue()
                                )
                        );
                    }

                    packet.getDataValueCollectionModifier().write(0, wrappedDataValueList);

                } catch (Exception e) {
                    packet.getWatchableCollectionModifier().write(0, dataWatcher.getWatchableObjects());
                }

                event.setPacket(packet);

            }
        });

         */
    }

    public static Hourglass getPlugin() {
        return plugin;
    }

}
