package com.josemarcellio.bedbreakeffect.effect;

import com.andrei1058.bedwars.api.events.player.PlayerBedBreakEvent;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.meta.FireworkMeta;

public class Fireworks extends PlayEffect {

    public Fireworks() {
        super ( "Firework");
    }

    @Override
    public void bedbreakeffect(PlayerBedBreakEvent e) {
        Location location = e.getVictimTeam ().getBed ();
        final Firework fw = (Firework) location.getWorld ().spawnEntity ( location, EntityType.FIREWORK );
        final FireworkMeta fwm = fw.getFireworkMeta ();
        fwm.setPower ( 10 );
    }
}
