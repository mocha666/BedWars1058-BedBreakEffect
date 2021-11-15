package com.josemarcellio.bedbreakeffect.inventory;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;

public class InventoryManager {
    private static final Map<Player, InventoryGUI> inventory = new HashMap<>();

    public static void openInventory(final Player p, final InventoryGUI g) {
        inventory.put(p, g);
    }

    public static void closeInventory(final Player p) {
        inventory.remove(p);
    }

    public static InventoryGUI getOpenInventory(final Player p) {
        return inventory.get(p);
    }
}