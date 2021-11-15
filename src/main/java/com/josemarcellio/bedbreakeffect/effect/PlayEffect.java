package com.josemarcellio.bedbreakeffect.effect;

import com.andrei1058.bedwars.api.events.player.PlayerBedBreakEvent;

public abstract class PlayEffect {

    protected String e;
    protected String effect;

    public PlayEffect(String effect) {
        this.effect = effect;
    }

    public String getEffect() {
        return effect;
    }

    public abstract void bedbreakeffect(PlayerBedBreakEvent e);

}
