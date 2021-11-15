package com.josemarcellio.bedbreakeffect.inventory;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

public class InventoryListener implements Listener {

    @EventHandler
    public void onInventoryClick(final InventoryClickEvent e) {
        if (e.getWhoClicked () instanceof Player && e.getSlotType ().equals ( InventoryType.SlotType.CONTAINER )) {
            final Player player = (Player) e.getWhoClicked ();
            final InventoryGUI gui = InventoryManager.getOpenInventory ( player );
            Inventory clickedInventory;
            clickedInventory = e.getInventory ();
            final int slot = e.getSlot ();
            if (gui != null && clickedInventory.equals ( gui.getInventory () ) && e.getInventory ().getItem ( slot ) != null) {
                e.setCancelled ( true );
                gui.clickGUI ( gui.getItemID ( slot ), player );
            }
        }
    }

    @EventHandler
    public void onInventoryClose(final InventoryCloseEvent e) {
        InventoryManager.closeInventory ( (Player) e.getPlayer () );
    }
}