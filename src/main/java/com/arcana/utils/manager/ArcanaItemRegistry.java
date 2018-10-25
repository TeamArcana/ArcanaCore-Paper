package com.arcana.utils.manager;

import com.arcana.utils.inventory.ArcanaItemstack;
import com.arcana.utils.user.PlayerBase;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * This is the manager for registering each ArcanaItemstack.
 */
public class ArcanaItemRegistry extends ArcanaManager<ArcanaItemstack> {

    private List<ArcanaItemstack> registeredItems;

    public ArcanaItemRegistry() {
        registeredItems = new CopyOnWriteArrayList<>();
    }

    /**
     * This method should be called from an inventory click listener.
     * @param itemStack
     * @param player
     * @return
     */
    public boolean handleIfItemExists(ItemStack itemStack, PlayerBase player){
        for(ArcanaItemstack item: registeredItems){
            if(item.is(itemStack)){
                item.action(player);
                return true;
            }
        }
        return false;
    }

    @Override
    public void addNoDuplicate(ArcanaItemstack arcanaItemstack) {

    }
}
