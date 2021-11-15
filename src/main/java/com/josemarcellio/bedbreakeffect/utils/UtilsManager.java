package com.josemarcellio.bedbreakeffect.utils;

import com.josemarcellio.bedbreakeffect.manager.EffectManager;
import org.bukkit.entity.Player;

public interface UtilsManager {
    void action(Player p, EffectManager em, String effectname, String permission, String effect);
    void action(Player p, EffectManager em, String effectname, String effect);
}
