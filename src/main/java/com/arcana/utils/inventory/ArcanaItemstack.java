package com.arcana.utils.inventory;

import com.arcana.ArcanaPaper;
import com.arcana.utils.user.PlayerBase;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * This is an itemstack with an action directly related to it. With the action occurring here,
 * it should keep the listener short and easy to read.
 */
public abstract class ArcanaItemstack extends ItemStack{

    public ArcanaItemstack(Material type) {
        super(type);
        register();
    }

    public ArcanaItemstack(Material type, int amount) {
        super(type, amount);
        register();
    }

    public ArcanaItemstack(ItemStack stack) throws IllegalArgumentException {
        super(stack);
        register();
    }

    private void register(){
        ArcanaPaper.INSTANCE.getItemRegistry().add(this);
    }

    public abstract void action(PlayerBase player);

    public boolean is(ItemStack itemStack){
        return itemStack instanceof ArcanaItemstack && this == itemStack && this.equals(itemStack);
    }
}
