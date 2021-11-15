package com.josemarcellio.bedbreakeffect;

import com.josemarcellio.bedbreakeffect.command.Command;
import com.josemarcellio.bedbreakeffect.command.CommandManager;
import com.josemarcellio.bedbreakeffect.configuration.*;
import com.josemarcellio.bedbreakeffect.economy.Economy;
import com.josemarcellio.bedbreakeffect.economy.Vault;
import com.josemarcellio.bedbreakeffect.inventory.InventoryListener;
import com.josemarcellio.bedbreakeffect.listeners.InGameListener;
import com.josemarcellio.bedbreakeffect.manager.EffectManager;
import com.josemarcellio.bedbreakeffect.metrics.Metrics;
import com.josemarcellio.bedbreakeffect.utils.Utils;
import com.josemarcellio.bedbreakeffect.utils.UtilsManager;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.Arrays;

public class BedBreakEffect extends JavaPlugin {

    public static BedBreakEffect getPlugins() {
        return JavaPlugin.getPlugin(BedBreakEffect.class);
    }

    private static UtilsManager utils;

    public static UtilsManager getUtils() {
        return utils;
    }

    private EffectManager effectManager;

    public EffectManager getEffectManager() {
        return effectManager;
    }

    private static Economy economy;

    public static Economy getEconomy() {
        return economy;
    }

    private final Messages messages = new Messages ( this );

    private final GUI gui = new GUI ( this );

    private final PlayerData playerdata = new PlayerData ( this );

    private final Sound sound = new Sound ( this );

    CommandManager register = new CommandManager (this);

    public void register(Listener... listeners) {
        Arrays.stream ( listeners ).forEach ( listener -> this.getServer ().getPluginManager ().registerEvents ( listener, this ) );
    }

    public void onLoad() {
        utils = new Utils ();
    }

    @Override
    public void onEnable() {
        if (!Bukkit.getPluginManager().isPluginEnabled("BedWars1058")) {
            getLogger().severe("BedWars1058 not found!");
            setEnabled(false);
            return;
        }

        new Metrics (this, 12864);

        register ( new InGameListener ( this ),
                new InventoryListener () );

        saveDefaultConfig ();
        new ConfigReader ( this );
        effectManager = new EffectManager ( this );

        RegisteredServiceProvider rsp = this.getServer ().getServicesManager ().getRegistration ( net.milkbowl.vault.economy.Economy.class );
        Vault.registerEconomy ( (net.milkbowl.vault.economy.Economy) rsp.getProvider () );
        economy = new Vault ();

        gui.load ();
        messages.load ();
        playerdata.load ();
        sound.load ();

        register.command(new Command (this));

        getLogger ().info ( "-------------------------------------------" );
        getLogger ().info ( "" );
        getLogger ().info ( "   BedWars1058-BedBreakEffect by JoseMarcellio" );
        getLogger ().info ( "" );
        getLogger ().info ( "-------------------------------------------" );
    }

    @Override
    public void onDisable() {
        getLogger ().info ( "Disabling BedWars-BedBreakEffect" );
        effectManager.saveEffects ();
    }
}


