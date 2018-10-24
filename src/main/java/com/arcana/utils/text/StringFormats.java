package com.arcana.utils.text;

import org.bukkit.ChatColor;

import java.util.Optional;

public class StringFormats {

    public static final StringFormat ERROR = new StringFormat(Optional.of(ChatColor.RED), Optional.empty());
    public static final StringFormat WARNING = new StringFormat(Optional.of(ChatColor.GOLD), Optional.empty());
    public static final StringFormat SUCCESS = new StringFormat(Optional.of(ChatColor.GREEN), Optional.empty());

    public static final StringFormat SECONDARY_TEXT = new StringFormat(Optional.of(ChatColor.GRAY), Optional.of(ChatColor.ITALIC));

}
