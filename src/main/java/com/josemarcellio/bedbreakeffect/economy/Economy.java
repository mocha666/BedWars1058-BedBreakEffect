package com.josemarcellio.bedbreakeffect.economy;

import org.bukkit.entity.Player;

public interface Economy {
    double getMoney(Player p);
    void takeMoney(Player p, double m);
}