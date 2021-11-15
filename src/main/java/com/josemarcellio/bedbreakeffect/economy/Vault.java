package com.josemarcellio.bedbreakeffect.economy;

import org.bukkit.entity.Player;

public class Vault implements Economy {

    private static net.milkbowl.vault.economy.Economy economy;

    @Override
    public double getMoney(Player p) {
        return economy.getBalance(p);
    }

    @Override
    public void takeMoney(Player p, double m) {
        economy.withdrawPlayer(p, m);
    }

    public static void registerEconomy(net.milkbowl.vault.economy.Economy economy) {
        Vault.economy = economy;
    }
}