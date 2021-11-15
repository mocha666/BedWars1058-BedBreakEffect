package com.josemarcellio.bedbreakeffect.configuration;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class ConfigReader {

    private final JavaPlugin plugin;

    public ConfigReader(JavaPlugin plugin) {
        this.plugin = plugin;
        readConfigs();
    }

    private void readConfigs() {
        for(Configs config : Configs.values()) {
            config.read(this);
        }
    }

    public FileConfiguration readConfig(String name) {
        File cg = new File(plugin.getDataFolder(), name);
        FileConfiguration fc = new YamlConfiguration();
        try {
            fc.load(cg);
            return fc;
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }

        return null;
    }

    public enum Configs {
        GUI("gui.yml"),
        PLAYERDATA("playerdata.yml"),
        SOUND("sound.yml");

        private final String name;
        private FileConfiguration config;

        Configs(String name) {
            this.name = name;
        }

        public void read (ConfigReader config) {
            this.config = config.readConfig(name);
        }

        public FileConfiguration getConfig() {
            return config;
        }
    }
}