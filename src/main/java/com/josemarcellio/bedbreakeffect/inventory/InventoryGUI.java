package com.josemarcellio.bedbreakeffect.inventory;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import com.josemarcellio.bedbreakeffect.configuration.ConfigReader;
import com.josemarcellio.bedbreakeffect.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public abstract class InventoryGUI {

    private final Map<Integer, Integer> items;
    private final Inventory inventory;
    private final InventoryGUI prevGui;

    public InventoryGUI(final String name, final int size, final InventoryGUI prevGui) {
        this.items = new HashMap<>();
        this.inventory = Bukkit.createInventory(null, size, name);
        this.prevGui = prevGui;
        this.init();
    }

    public InventoryGUI(final String name, final int size) {
        this(name, size, null);
    }

    public abstract void init();

    public abstract void clickGUI(final int id, final Player player);

    public int calcPos(int x, int y) {
        return (x - 1) + (9 * (y - 1));
    }

    public int getItemID(final int slot) {
        return items.get(slot);
    }

    public void open(final Player player) {
        player.openInventory(this.inventory);
        InventoryManager.openInventory(player, this);
    }

    public void setGUI(final int id, final ItemStack item, final int slot) {
        this.inventory.setItem(slot, item);
        this.items.put(slot, id);
    }

    public void setGUI(final int id, final ItemStack item, final int x, final int y) {
        this.setGUI(id, item, this.calcPos(x, y));
    }

    public ItemStack createItem(final String effect) {
        FileConfiguration gui = ConfigReader.Configs.GUI.getConfig();
        final ItemStack item = new ItemStack(Material.valueOf(gui.getString(effect + ".Material")), 1);
        item.setDurability((short)gui.getInt(effect + ".Data"));
        final ItemMeta meta = item.getItemMeta();
        meta.setDisplayName( Utils.getColor(gui.getString(effect + ".Name")));
        meta.setLore(Arrays.asList(Utils.getColor(gui.getString(effect + ".Lore")).split("\n")));
        item.setItemMeta(meta);
        return item;
    }

    public Inventory getInventory() {
        return this.inventory;
    }

    public void close(final Player player) {
        player.closeInventory();
        InventoryManager.closeInventory(player);
    }

    public void back(final Player player) {
        if (this.prevGui != null) {
            this.prevGui.open(player);
        } else {
            this.close(player);
        }
    }
}
