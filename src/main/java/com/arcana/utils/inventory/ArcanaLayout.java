package com.arcana.utils.inventory;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

/**
 * This is an Inventory layout. We can easily create these and cache them. Simply using apply(Inventory inventory)
 * will apply this layout to the given inventory.
 */
public class ArcanaLayout {

    private ItemStack[][] layout;
    private int rows;

    public ArcanaLayout(int rows){
        if(rows > 9){
            rows = 9;
        }

        this.rows = rows;

        layout = new ItemStack[9][rows];
    }

    public ArcanaLayout(ArcanaLayout layout){
        this.rows = layout.rows;
        this.layout = Arrays.copyOf(layout.layout, layout.layout.length);
    }

    public void apply(Inventory inventory){
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < rows; j++){
                int index = (i * 9) + j;
                if(layout[i][j] != null) {
                    inventory.setItem(index, layout[i][j]);
                } else {
                    inventory.clear(index);
                }
            }
        }
    }

    public void placeItem(ItemStack item, int x, int y){
        layout[x][y] = item;
    }

    public void addFillerRow(ItemStack filler, LayoutRowDirection direction, int rowIndex){
        if(rowIndex > 8){
            return;
        }

        if(direction == LayoutRowDirection.HORIZONTAL){
            for(int i = 0; i < 9; i++){
                layout[i][rowIndex] = filler;
            }
        } else if(direction == LayoutRowDirection.VERTICAL){
            //Vertical is 2nd dimension
            for(int i = 0; i < 9; i++){
                layout[rowIndex][i] = filler;
            }
        }
    }

    public void fillAll(ItemStack filler){
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < rows; j++){
                layout[i][j] = filler;
            }
        }
    }

    public void fillEmpty(ItemStack filler){
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < rows; j++){
                if(layout[i][j] == null) {
                    layout[i][j] = filler;
                }
            }
        }
    }

    public enum LayoutRowDirection{
        HORIZONTAL,
        VERTICAL
    }

}
