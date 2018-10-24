package com.arcana.utils.text;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Optional;

public class Messager {

    public static void broadcast(String text, Optional<Prefix> prefix){
        if(prefix.isPresent()){
            text = prefix.get().getText() + text;
        }

        for(Player player: Bukkit.getOnlinePlayers()){
            player.sendMessage(text);
        }
    }

    public static void sendMessage(Player player, String s) {
        player.sendMessage(s);
    }

    public static void sendMessage(Message message){
        for(Player player: message.getReceivers()){
            for(String s: message.getStrings()){
                sendMessage(player, s);
            }
        }
    }

    public static class Prefix{
        public static Prefix ERROR = new Prefix(ChatColor.RED + ChatColor.BOLD.toString() + "[" + AltCodes.THICK_X.getSign() + "] ");
        public static Prefix INFO =  new Prefix(ChatColor.GOLD + ChatColor.BOLD.toString() + "[!] ");
        public static Prefix SUCCESS =  new Prefix(ChatColor.GREEN + ChatColor.BOLD.toString() + "[" + AltCodes.CHECKMARK.getSign() + "] ");
        public static Prefix ECO =  new Prefix(ChatColor.GOLD + ChatColor.BOLD.toString() + "[" + ChatColor.GREEN + ChatColor.BOLD.toString()
                + "ECO" + ChatColor.GOLD + ChatColor.BOLD.toString() + "] ");
        public static Prefix CHAT =  new Prefix(ChatColor.GOLD + ChatColor.BOLD.toString() + "[" + ChatColor.GRAY + ChatColor.BOLD.toString()
                + "CHAT" + ChatColor.GOLD + ChatColor.BOLD.toString() + "] ");
        private String text;

        Prefix(String text){this.text = text;}

        public String getText() {
            return text;
        }
    }

}
