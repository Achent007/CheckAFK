package be.achent.checkafk.messages;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;

import be.achent.checkafk.CheckAFK;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Messages {
    private FileConfiguration languageConfig = null;

    private File languageConfigFile = null;

    public void reload() {
        CheckAFK plugin = CheckAFK.getInstance();
        if (this.languageConfigFile == null)
            this.languageConfigFile = new File(plugin.getDataFolder(), "language.yml");
        this.languageConfig = (FileConfiguration)YamlConfiguration.loadConfiguration(this.languageConfigFile);
        Reader defConfigStream = null;
        try {
            defConfigStream = new InputStreamReader(plugin.getResource("language.yml"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        if (defConfigStream != null) {
            YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
            this.languageConfig.setDefaults((Configuration)defConfig);
        }
    }

    public FileConfiguration get() {
        if (this.languageConfig == null)
            reload();
        return this.languageConfig;
    }

    public void saveConfig() {
        if (this.languageConfig == null || this.languageConfigFile == null)
            return;
        try {
            get().save(this.languageConfigFile);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void saveDefaultConfig() {
        CheckAFK plugin = CheckAFK.getInstance();
        if (this.languageConfigFile == null)
            this.languageConfigFile = new File(plugin.getDataFolder(), "language.yml");
        if (!this.languageConfigFile.exists())
            plugin.saveResource("language.yml", false);
    }
}
