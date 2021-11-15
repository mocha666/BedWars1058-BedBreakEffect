package com.josemarcellio.bedbreakeffect.listeners;

import com.andrei1058.bedwars.api.events.player.PlayerBedBreakEvent;
import com.josemarcellio.bedbreakeffect.BedBreakEffect;
import com.josemarcellio.bedbreakeffect.effect.PlayEffect;
import com.josemarcellio.bedbreakeffect.manager.EffectManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class InGameListener implements Listener {

    private final BedBreakEffect plugin;

    public InGameListener(BedBreakEffect plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void pickupbedbreakeffect(PlayerBedBreakEvent e) {
        EffectManager effectManager = plugin.getEffectManager ();
        Player p = e.getPlayer();
        PlayEffect play = effectManager.getEffect ( p );
        if (play != null) {
            play.bedbreakeffect (e);
        }
    }
}
