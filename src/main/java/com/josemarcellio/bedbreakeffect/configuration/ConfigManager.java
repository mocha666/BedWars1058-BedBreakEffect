package com.josemarcellio.bedbreakeffect.configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

public class ConfigManager {
    private final File file;
    private final FileConfiguration configuration;

    public ConfigManager(JavaPlugin plugin, String config) {
        this.file = new File ( plugin.getDataFolder (), config );
        if (!file.exists ()) {
            if (plugin.getResource ( config ) == null) {
                try {
                    file.createNewFile ();
                } catch (IOException e) {
                    e.printStackTrace ();
                }
            } else plugin.saveResource ( config, false );
        }
        configuration = YamlConfiguration.loadConfiguration ( file );
    }

    public void load() {
        for (Field f : getClass ().getDeclaredFields ()) {
            if (!f.isAnnotationPresent ( BedBreakEffectConfig.class )) continue;
            try {
                BedBreakEffectConfig source = f.getAnnotation ( BedBreakEffectConfig.class );
                f.set ( this, configuration.get ( source.path () ) );
            } catch (IllegalAccessException e) {
                e.printStackTrace ();
            }
        }
    }
}