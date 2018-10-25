package com.arcana.utils.inventory;

import com.arcana.ArcanaPaper;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * This is an ItemStack with an action directly related to it. The action will directly be called from the
 * ArcanaItemRegistry instance, so no extra work is needed to be done to perform the action.
 */
public abstract class ArcanaItemstack extends ItemStack{

    /**
     * If this ArcanaItemstack should be removed from the ArcanaItemRegistry once the action is done.
     */
    private boolean removeOnAction;

    public ArcanaItemstack(Material type, boolean removeOnAction) {
        super(type);

        this.removeOnAction = removeOnAction;
        register();
    }

    public ArcanaItemstack(Material type, int amount, boolean removeOnAction) {
        super(type, amount);

        this.removeOnAction = removeOnAction;
        register();
    }

    public ArcanaItemstack(ItemStack stack, boolean removeOnAction) throws IllegalArgumentException {
        super(stack);

        this.removeOnAction = removeOnAction;
        register();
    }

    private void register(){
        ArcanaPaper.INSTANCE.getItemRegistry().add(this);
    }

    protected abstract void action(Player player);

    public boolean is(ItemStack itemStack){
        return itemStack instanceof ArcanaItemstack && this == itemStack && this.equals(itemStack);
    }

    public void performAction(Player player){
        action(player);

        if(removeOnAction){
            ArcanaPaper.INSTANCE.getItemRegistry().remove(this);
        }
    }
}
