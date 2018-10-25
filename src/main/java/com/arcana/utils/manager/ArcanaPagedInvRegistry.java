package com.arcana.utils.manager;

import com.arcana.utils.inventory.ArcanaPagedInventory;
import org.bukkit.inventory.Inventory;

import java.util.Optional;

public class ArcanaPagedInvRegistry extends ArcanaManager<ArcanaPagedInventory> {

    @Override
    public void addNoDuplicate(ArcanaPagedInventory arcanaPagedInventory) {

    }

    public Optional<ArcanaPagedInventory> getInventory(Inventory current){
        for(ArcanaPagedInventory p: objects){
            if(p.isPage(current)){
                return Optional.of(p);
            }
        }
        return Optional.empty();
    }

    public void remove(Inventory current){
        for(ArcanaPagedInventory p: objects){
            if(p.isPage(current)){
                remove(p);
                return;
            }
        }
    }
}
