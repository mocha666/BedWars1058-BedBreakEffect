package com.josemarcellio.bedbreakeffect.configuration;

import org.bukkit.plugin.java.JavaPlugin;

public class GUI extends ConfigManager {

    public GUI(JavaPlugin plugin) {
        super(plugin, "gui.yml");
    }

    @BedBreakEffectConfig (path = "GUI.Name")
    public static String GUI_NAME;

    @BedBreakEffectConfig (path = "GUI.Size")
    public static int GUI_SIZE;

}
