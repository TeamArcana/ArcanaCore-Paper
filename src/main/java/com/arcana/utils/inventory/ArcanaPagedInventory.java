package com.arcana.utils.inventory;

import com.arcana.ArcanaPaper;
import org.bukkit.inventory.Inventory;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * This is essentially a "book" of inventories, each linked to the one before it and after it.
 */
public class ArcanaPagedInventory {

    private List<Inventory> pages;

    public ArcanaPagedInventory(){
        this(null);
    }

    public ArcanaPagedInventory(Inventory... pages){
        this.pages = new CopyOnWriteArrayList<>();
        if(pages != null){
            this.pages.addAll(Arrays.asList(pages));
        }
        ArcanaPaper.INSTANCE.getPagedInvRegistry().add(this);
    }

    public Inventory open(){
        return pages.get(0);
    }

    public boolean isPage(Inventory inventory){
        return pages.contains(inventory);
    }

    public Optional<Inventory> nextPage(Inventory current){
        if(isPage(current)){
            int index = pages.indexOf(current);
            if(index < pages.size()  -1){
                return Optional.of(pages.get(index + 1));
            }
        }
        return Optional.empty();
    }

    public Optional<Inventory> previousPage(Inventory current){
        if(isPage(current)){
            int index = pages.indexOf(current);
            if(index < 0){
                return Optional.of(pages.get(index - 1));
            }
        }
        return Optional.empty();
    }

}
