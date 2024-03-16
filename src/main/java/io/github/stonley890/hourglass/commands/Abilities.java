package io.github.stonley890.hourglass.commands;

import io.github.stonley890.hourglass.Ability;
import io.github.stonley890.hourglass.player.PlayerMemory;
import io.github.stonley890.hourglass.player.PlayerUtility;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Abilities implements CommandExecutor {

    List<ChatColor> colors = new ArrayList<>();
    public static List<Ability> abilities = new ArrayList<>();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String[] args) {

        // Color shortcuts
        colors.add(ChatColor.RED);
        colors.add(ChatColor.GREEN);
        colors.add(ChatColor.BLUE);
        colors.add(ChatColor.DARK_PURPLE);

        if (sender instanceof Player) {

            // Get player memory
            Player player = (Player) sender;
            PlayerMemory memory = PlayerUtility.getPlayerMemory(player);

            // Change player memory if ability is set
            if (args.length == 2) {
                try {
                    int abilityIndex = Integer.parseInt(args[0]);
                    int abilityValue = Integer.parseInt(args[1]);

                    if (abilityValue <= abilities.get(abilityIndex).getLevels()) {
                        memory.setAbility(abilityIndex, abilityValue);
                        player.sendMessage(ChatColor.RED + abilities.get(abilityIndex).getName() + ChatColor.WHITE + " set to level " + ChatColor.GOLD + abilityValue);
                    }
                } catch (NumberFormatException e) {
                    // Ints could not be parsed
                    player.sendMessage(ChatColor.RED + "Invalid syntax! Use the gui menu: /abilities");
                }
            }

            // Create book gui
            ItemStack gui = new ItemStack(Material.WRITTEN_BOOK);
            BookMeta meta = (BookMeta) gui.getItemMeta();

            BaseComponent[] component = new BaseComponent[0];

            for (Ability ability : abilities) {

                ComponentBuilder levels = new ComponentBuilder();

                // Create levels with ClickEvents
                for (int i = 0; i <= ability.getLevels(); i++) {
                    // Underline the value that matches the player's ability level (from PlayerMemory)
                    levels.append(String.valueOf(i)).color(colors.get(i)).underlined(memory.getAbility(abilities.indexOf(ability)) == i).event(new ClickEvent(
                            ClickEvent.Action.RUN_COMMAND, "/abilities " + abilities.indexOf(ability) + " " + i
                    ));
                    if (i != ability.getLevels()) {
                        levels.append(" | ").color(ChatColor.DARK_GRAY).underlined(false);
                    }
                }

                // Build page
                component = new ComponentBuilder()
                        .append(ability.getName()).color(ChatColor.RED).bold(true)
                        .append("\n[ ").bold(false).color(ChatColor.DARK_GRAY).italic(false)
                        .append(levels.create())
                        .append(" ]\n").color(ChatColor.DARK_GRAY).underlined(false).event((ClickEvent) null)
                        .append(ability.getLore()).color(ChatColor.DARK_AQUA).italic(true).append("\n")
                        .append(ability.getDetails())
                        .create();

                meta.spigot().addPage(component);
            }

            meta.setAuthor("Hourglass");
            meta.setGeneration(BookMeta.Generation.ORIGINAL);
            meta.setTitle("Abilities");



            // Set and open book gui
            gui.setItemMeta(meta);
            player.openBook(gui);
            Ability.reload(player);

        }

        return true;
    }
}
