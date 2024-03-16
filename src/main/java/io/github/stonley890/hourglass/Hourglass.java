package io.github.stonley890.hourglass;

// import com.comphenix.protocol.ProtocolLibrary;
// import com.comphenix.protocol.ProtocolManager;
import io.github.stonley890.hourglass.abilities.*;
import io.github.stonley890.hourglass.commands.Abilities;
import io.github.stonley890.hourglass.listeners.ListenPlayerRespawn;
import io.github.stonley890.hourglass.listeners.PlayerJoin;
import io.github.stonley890.hourglass.listeners.PlayerLeave;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;

public final class Hourglass extends JavaPlugin {

    static Hourglass plugin;

    // private ProtocolManager protocolManager;
    Set<Integer> glowIds = new HashSet<>();
    public static List<Material> meatFoods = new ArrayList<>();
    public static double defaultMovementSpeed = 0.10000000149011612;
    public static ItemStack hourglassItem = new ItemStack(Material.KNOWLEDGE_BOOK);

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;
        // protocolManager = ProtocolLibrary.getProtocolManager();

        // Commands
        Objects.requireNonNull(getCommand("abilities")).setExecutor(new Abilities());

        // Listeners
        getServer().getPluginManager().registerEvents(new Adrift(), this);
        getServer().getPluginManager().registerEvents(new Bound(), this);
        getServer().getPluginManager().registerEvents(new ArmoredWings(), this);
        getServer().getPluginManager().registerEvents(new ListenPlayerRespawn(), this);
        getServer().getPluginManager().registerEvents(new EnderiansEmbrace(), this);
        getServer().getPluginManager().registerEvents(new EnderiansPacifist(), this);
        getServer().getPluginManager().registerEvents(new EnderiansPacifist(), this);
        getServer().getPluginManager().registerEvents(new Inflammable(), this);
        getServer().getPluginManager().registerEvents(new NimbleDescent(), this);
        getServer().getPluginManager().registerEvents(new Phytophage(), this);
        getServer().getPluginManager().registerEvents(new Predator(), this);
        getServer().getPluginManager().registerEvents(new TenderTouch(), this);
        getServer().getPluginManager().registerEvents(new UnfathomableLuck(), this);

        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
        getServer().getPluginManager().registerEvents(new PlayerLeave(), this);

        // Create list of abilities
        Abilities.abilities.add(new Adrift());
        Abilities.abilities.add(new ArmoredWings());
        Abilities.abilities.add(new Bound());
        Abilities.abilities.add(new Burrowed());
        Abilities.abilities.add(new Crepescule());
        Abilities.abilities.add(new Cultivate());
        Abilities.abilities.add(new CrystalSense());
        Abilities.abilities.add(new EnderiansEmbrace());
        Abilities.abilities.add(new EnderiansPacifist());
        Abilities.abilities.add(new Extrasensory());
        Abilities.abilities.add(new Flare());
        Abilities.abilities.add(new FrigidFate());
        Abilities.abilities.add(new Heatwave());
        Abilities.abilities.add(new Hydrophobia());
        Abilities.abilities.add(new Inflammable());
        Abilities.abilities.add(new Minimized());
        Abilities.abilities.add(new NimbleDescent());
        Abilities.abilities.add(new Phytophage());
        Abilities.abilities.add(new Predator());
        Abilities.abilities.add(new Swiftfeet());
        Abilities.abilities.add(new TenderTouch());
        Abilities.abilities.add(new TheHourglass());
        Abilities.abilities.add(new UnfathomableLuck());

        Runnable repeatTask = new BukkitRunnable() {
            @Override
            public void run() {
                Ability.loopTask();
            }
        };

        // Run repeat task once per second
        Bukkit.getScheduler().runTaskTimer(this, repeatTask, 1, 20);

        // Run every 20s
        Bukkit.getScheduler().runTaskTimer(this, () -> {
            for (Ability ability : Abilities.abilities) {
                if (ability instanceof Flare flare)
                    for (Player player : Bukkit.getOnlinePlayers())
                        flare.loop20(player);
                if (ability instanceof TheHourglass hourglass) {
                    for (Player player : Bukkit.getOnlinePlayers())
                        hourglass.loop20(player);
                }
            }
        }, 1, 400);

        // Run every 1 tick
        Bukkit.getScheduler().runTaskTimer(this, () -> {
            for (Ability ability : Abilities.abilities) {
                if (ability instanceof UnfathomableLuck luck)
                    for (Player player : Bukkit.getOnlinePlayers())
                        luck.loopTick(player);
            }
        }, 1, 1);

        meatFoods.add(Material.BEEF);
        meatFoods.add(Material.COOKED_BEEF);
        meatFoods.add(Material.PORKCHOP);
        meatFoods.add(Material.COOKED_PORKCHOP);
        meatFoods.add(Material.MUTTON);
        meatFoods.add(Material.COOKED_MUTTON);
        meatFoods.add(Material.CHICKEN);
        meatFoods.add(Material.COOKED_CHICKEN);
        meatFoods.add(Material.RABBIT);
        meatFoods.add(Material.COOKED_RABBIT);
        meatFoods.add(Material.COD);
        meatFoods.add(Material.COOKED_COD);
        meatFoods.add(Material.SALMON);
        meatFoods.add(Material.COOKED_SALMON);
        meatFoods.add(Material.ROTTEN_FLESH);
        meatFoods.add(Material.RABBIT_STEW);

        Objects.requireNonNull(hourglassItem.getItemMeta()).setCustomModelData(401135);

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
