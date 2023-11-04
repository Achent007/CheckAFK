package be.achent.checkafk;

import be.achent.checkafk.commands.CheckAFKCommands;
import be.achent.checkafk.messages.Messages;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class CheckAFK extends JavaPlugin {

    public CheckAFKCommands commands;

    private Messages messages;

    public static CheckAFK plugin;

    public void onEnable() {
        plugin = this;
        this.messages = new Messages();
        this.commands = new CheckAFKCommands();
        saveDefaultConfig();
        this.messages.saveDefaultConfig();
    }

    public void saveDefaultConfig() {
        this.messages.saveDefaultConfig();
    }

    public static CheckAFK getInstance() {
        return plugin;
    }

    public String getMessage(String path) {
        return ChatColor.translateAlternateColorCodes('&', this.messages.get().getString(path));
    }

    public void reloadMessages() {
        this.messages.reload();
    }

    public void saveDefaultsMessages() {
        this.messages.saveDefaultConfig();
    }

    public static CheckAFK getPlugin() {
        return plugin;
    }

}