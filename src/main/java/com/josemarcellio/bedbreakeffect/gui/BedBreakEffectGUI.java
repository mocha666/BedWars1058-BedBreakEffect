package com.josemarcellio.bedbreakeffect.gui;

import com.josemarcellio.bedbreakeffect.BedBreakEffect;
import com.josemarcellio.bedbreakeffect.configuration.ConfigReader;
import com.josemarcellio.bedbreakeffect.configuration.GUI;
import com.josemarcellio.bedbreakeffect.inventory.InventoryGUI;
import com.josemarcellio.bedbreakeffect.utils.Utils;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class BedBreakEffectGUI extends InventoryGUI {

    private final BedBreakEffect plugin;

    public BedBreakEffectGUI(BedBreakEffect plugin) {
        super( Utils.getColor ( GUI.GUI_NAME),
                GUI.GUI_SIZE);
        this.plugin = plugin;
    }


    @Override
    public void init() {
        FileConfiguration gui = ConfigReader.Configs.GUI.getConfig();
        this.setGUI(1,
                this.createItem("None" ), gui.getInt("None.Location.X"),
                gui.getInt("None.Location.Y"));

        this.setGUI(2,
                this.createItem("PigMissile" ), gui.getInt("PigMissile.Location.X"),
                gui.getInt("PigMissile.Location.Y"));

        this.setGUI(3,
                this.createItem("SquidMissile" ), gui.getInt("SquidMissile.Location.X"),
                gui.getInt("SquidMissile.Location.Y"));

        this.setGUI(4,
                this.createItem("LightningStrike" ), gui.getInt("LightningStrike.Location.X"),
                gui.getInt("LightningStrike.Location.Y"));

        this.setGUI(5,
                this.createItem("Firework" ), gui.getInt("Firework.Location.X"),
                gui.getInt("Firework.Location.Y"));

        this.setGUI(13,
                this.createItem("Close" ), gui.getInt("Close.Location.X"),
                gui.getInt("Close.Location.Y"));
    }

    @Override
    public void clickGUI(int id, Player p) {
        switch (id) {
            case 1:
                BedBreakEffect.getUtils().action(p, plugin.getEffectManager (),
                        "NONE", "NONE");
                break;
            case 2:
                BedBreakEffect.getUtils().action(p, plugin.getEffectManager (),
                         "Pig Missile",
                        "pigmissile", "PigMissile");
                break;
            case 3:
                BedBreakEffect.getUtils().action(p, plugin.getEffectManager (),
                        "Squid Missile",
                        "squidmissile", "SquidMissile");
                break;
            case 4:
                BedBreakEffect.getUtils().action(p, plugin.getEffectManager (),
                        "Lightning Strike",
                        "lightningstrike", "LightningStrike");
                break;
            case 5:
                BedBreakEffect.getUtils().action(p, plugin.getEffectManager (),
                        "Firework",
                        "firework", "Firework");
                break;
            case 13:
                this.close(p);
                break;
        }
    }
}
