package com.josemarcellio.bedbreakeffect.configuration;

import org.bukkit.plugin.java.JavaPlugin;

public class Messages extends ConfigManager {

    public Messages(JavaPlugin plugin) {
        super(plugin, "messages.yml");
    }

    @BedBreakEffectConfig (path = "MainCommand")
    public static String MAIN_COMMAND;

    @BedBreakEffectConfig (path = "AdminCommand")
    public static String ADMIN_COMMAND;

    @BedBreakEffectConfig (path = "OnlyPlayerCanUseCommand")
    public static String ONLY_PLAYER_CAN_USE_COMMAND;

    @BedBreakEffectConfig (path = "NoPermission")
    public static String NO_PERMISSION;

    @BedBreakEffectConfig (path = "SelectedEffect")
    public static String SELECTED_EFFECT;

    @BedBreakEffectConfig (path = "LockedEffect")
    public static String LOCKED_EFFECT;

    @BedBreakEffectConfig (path = "PurchasedEffect")
    public static String PURCHASED_EFFECT;
}
