package com.arcana.utils.text;

import org.bukkit.ChatColor;

import java.util.Optional;

/**
 * This is a basic format that can be applied to any string. Should save having to type the same
 * colors and styles over and over.
 */
public class StringFormat {

    private Optional<ChatColor> color, style;

    public StringFormat(Optional<ChatColor> color, Optional<ChatColor> style) {
        this.color = color;
        this.style = style;
    }

    public String apply(String string){
        if(color.isPresent()){
            string = color.get() + string;
        }

        if(style.isPresent()){
            string = style.get() + string;
        }

        return string;
    }
}
