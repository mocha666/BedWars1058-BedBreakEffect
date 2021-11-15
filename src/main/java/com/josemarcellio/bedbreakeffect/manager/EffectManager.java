package com.josemarcellio.bedbreakeffect.manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.josemarcellio.bedbreakeffect.BedBreakEffect;
import com.josemarcellio.bedbreakeffect.configuration.ConfigReader;
import com.josemarcellio.bedbreakeffect.effect.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class EffectManager {

    private final List<PlayEffect> playEffect;
    private HashMap<String, PlayEffect> playEffectMap;
    private final BedBreakEffect plugin;

    public EffectManager(BedBreakEffect plugin) {
        playEffect = new ArrayList<>();
        registerEffect(new PigMissile ());
        registerEffect(new SquidMissile ());
        registerEffect(new LightningStrike ());
        registerEffect(new Fireworks ());

        this.plugin = plugin;
        loadEffects();
    }

    public void loadEffects() {
        playEffectMap = new HashMap<>();
        FileConfiguration playerdata = ConfigReader.Configs.PLAYERDATA.getConfig();
        for (String uuid : playerdata.getKeys(false)) {
            String effect = playerdata.getString(uuid + ".selected");
            playEffectMap.put(uuid, getEffects(effect));
        }
    }

    public void saveEffects() {
        FileConfiguration playerdata = ConfigReader.Configs.PLAYERDATA.getConfig();
        for (String uuid : playEffectMap.keySet()) {
            playerdata.set(uuid + ".selected", playEffectMap.get(uuid).getEffect());
        }
        try {
            playerdata.save(plugin.getDataFolder () + "/playerdata.yml");
        } catch (IOException e) {
            e.printStackTrace ();
        }
    }

    public void registerEffect(PlayEffect effectManager) {
        playEffect.add(effectManager);
    }

    public PlayEffect getEffects(String effect) {
        for (PlayEffect effectManager : playEffect) {
            if (effectManager.getEffect().equalsIgnoreCase(effect)) {
                return effectManager;
            }
        }
        return null;
    }

    public PlayEffect getEffect(Player p) {
        String uuid = p.getUniqueId().toString();
        if (playEffectMap.containsKey(uuid)) {
            return playEffectMap.get(uuid);
        }
        return null;
    }

    public void setEffect(Player p, String effect) {
        String uuid = p.getUniqueId().toString();
        playEffectMap.remove(uuid);
        PlayEffect playeffect = getEffects(effect);
        if (playeffect != null) {
            playEffectMap.put(uuid, playeffect);
        }
    }
}
