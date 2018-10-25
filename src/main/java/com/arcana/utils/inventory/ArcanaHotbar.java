package com.arcana.utils.inventory;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

/**
 * This a layout for the hotbar. Easily created, cached and applied.
 */
public class ArcanaHotbar {

    private ItemStack[] items;

    public ArcanaHotbar() {
        items = new ItemStack[9];
    }

    public ArcanaHotbar add(ItemStack item, int index){
        items[index] = item;
        return this;
    }

    public void apply(Inventory inventory){
        for(int i = 0; i < 9; i++){
            if(items[i] != null){
                inventory.setItem(i, items[i]);
            } else {
                inventory.clear(i);
            }
        }
    }
}
