package com.josemarcellio.bedbreakeffect.configuration;

import org.bukkit.plugin.java.JavaPlugin;

public class Sound extends ConfigManager {

    public Sound(JavaPlugin plugin) {
        super(plugin, "sound.yml");
    }

    @BedBreakEffectConfig (path = "SelectedEffect")
    public static String SELECTED_EFFECT;

    @BedBreakEffectConfig (path = "LockedEffect")
    public static String LOCKED_EFFECT;

    @BedBreakEffectConfig (path = "PurchasedEffect")
    public static String PURCHASED_EFFECT;
}
