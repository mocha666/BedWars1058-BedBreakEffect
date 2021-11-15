package com.josemarcellio.bedbreakeffect.effect;

import com.andrei1058.bedwars.api.events.player.PlayerBedBreakEvent;
import org.bukkit.*;
public class LightningStrike extends PlayEffect {

    public LightningStrike() {
        super ( "LightningStrike");
    }

    @Override
    public void bedbreakeffect(PlayerBedBreakEvent e) {
        Location location = e.getVictimTeam ().getBed ();
        location.getWorld ().strikeLightningEffect ( location );
    }
}
