package com.arcana.utils.manager;

import com.arcana.ArcanaPaper;
import com.arcana.utils.inventory.ArcanaItemstack;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * This is the manager for registering each ArcanaItemstack. This will directly handle calling the action
 * for an ArcanaItemStack that is clicked on.
 */
public class ArcanaItemRegistry extends ArcanaManager<ArcanaItemstack> implements Listener {

    private List<ArcanaItemstack> registeredItems;

    public ArcanaItemRegistry() {
        registeredItems = new CopyOnWriteArrayList<>();
        Bukkit.getServer().getPluginManager().registerEvents(this, ArcanaPaper.INSTANCE);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onClick(InventoryClickEvent event){
        if(event.getWhoClicked() instanceof Player){
            if(handleIfItemExists(event.getCurrentItem(), (Player) event.getWhoClicked())) {
                event.setCancelled(true);
            }
        }
    }

    /**
     * This method should be called from an inventory click listener.
     * @param itemStack
     * @param player
     * @return
     */
    private boolean handleIfItemExists(ItemStack itemStack, Player player){
        for(ArcanaItemstack item: registeredItems){
            if(item.is(itemStack)){
                item.performAction(player);
                return true;
            }
        }
        return false;
    }

    @Override
    public void addNoDuplicate(ArcanaItemstack arcanaItemstack) {

    }
}
