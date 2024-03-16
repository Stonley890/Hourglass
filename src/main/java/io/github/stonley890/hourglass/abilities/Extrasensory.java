package io.github.stonley890.hourglass.abilities;

// import com.comphenix.protocol.PacketType;
// import com.comphenix.protocol.ProtocolLibrary;
// import com.comphenix.protocol.events.PacketContainer;
// import com.comphenix.protocol.wrappers.WrappedDataWatcher;
// import com.comphenix.protocol.wrappers.WrappedWatchableObject;
import io.github.stonley890.hourglass.Ability;
import io.github.stonley890.hourglass.player.PlayerMemory;
import io.github.stonley890.hourglass.player.PlayerUtility;
import io.github.stonley890.hourglass.protocol.EntityData;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Random;

public class Extrasensory extends Ability {
    final String name = "Extrasensory";
    final String lore = "Tuned to the presence of those around you, your powerful senses allow you to perceive hidden threats.";
    final int levels = 1;
    final BaseComponent[] details = new ComponentBuilder().append("You are able to highlight enemies up to ").color(ChatColor.BLACK).append("20").color(ChatColor.GREEN).append(" meters away when sneaking.").color(ChatColor.BLACK).create();
    @Override
    public void init(Player player) {

    }

    @Override
    public void loop(Player player) {

        /*PlayerMemory memory = PlayerUtility.getPlayerMemory(player);

        if (memory.getAbility(this) == 1) {
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

            }


        }
        */
    }

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
}
