package be.achent.checkafk.commands;

import be.achent.checkafk.CheckAFK;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.io.File;

public class CheckAFKCommands implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        CheckAFK plugin = CheckAFK.getInstance();
        if (args.length == 0) {
            if (sender.hasPermission("checkafk.reload") || sender.hasPermission("checkafk.help")) {
                sender.sendMessage("");
                sender.sendMessage(ChatColor.GOLD + "/checkafk" + ChatColor.WHITE + ": Commandes de CheckAFK");
                sender.sendMessage(ChatColor.GOLD + "/checkafk reload" + ChatColor.WHITE + ": Recharge le fichier de config.");
                sender.sendMessage("");
            } else {
                sender.sendMessage(plugin.getMessage("NoPermission"));
            }
        } else if (args[0].equalsIgnoreCase("reload")) {
            if (sender.hasPermission("checkafk.reload")) {
                if (args.length == 1) {
                    File config = new File(plugin.getDataFolder(), "config.yml");
                    File language = new File(plugin.getDataFolder(), "language.yml");
                    if (!config.exists()) {
                        plugin.saveDefaultConfig();
                    } else {
                        plugin.reloadConfig();
                    }
                    if (!language.exists()) {
                        plugin.saveDefaultsMessages();
                    } else {
                        plugin.reloadMessages();
                    }
                    sender.sendMessage(plugin.getMessage("Reloaded"));
                } else {
                    sender.sendMessage("reload");
                }
            } else {
                sender.sendMessage(plugin.getMessage("NoPermission"));
            }
        }
        return false;
    }
}
