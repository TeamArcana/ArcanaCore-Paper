package com.arcana.utils;

import org.bukkit.ChatColor;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Utils for all String functions.
 */
public class StringUtils {

    /**
     * This gets a random blank string. Good use for blank scoreboard lines or Team entries.
     * @return
     */
    public static String getRandomEmptyString(){
        Random random = new Random();
        String give = "";
        for(int i = 0; i < 5; i++){
            give += ChatColor.values()[random.nextInt(ChatColor.values().length)] + "";
        }
        return give;
    }

    /**
     * Capitalizes the first letter
     * @param string
     * @return
     */
    public static String capitalizeFirstLetter(String string){
        string = string.toLowerCase();
        return string.substring(0, 1).toUpperCase() + string.substring(1);
    }

    /**
     * Replaces the spaces in a string with underscores
     * @param string
     * @return
     */
    public static String replaceSpacesWithUnderscores(String string){
        return string.replace(" ", "_");
    }

    /**
     * Replaces spaces in each string with underscores
     * @param strings
     * @return
     */
    public static String[] replaceSpacesWithUnderscores(String[] strings){
        String[] give = new String[strings.length];
        for(int i = 0; i < give.length; i++){
            give[i] = replaceSpacesWithUnderscores(strings[i]);
        }
        return give;
    }

    /**
     * Nicely formats the name of a given Enum
     * @param e
     * @param replaceUnderscores
     * @return
     */
    public static String enumToString(Enum e, boolean replaceUnderscores){
        String give = e.toString();

        if(replaceUnderscores && give.contains("_")){
            String[] temp = give.split("_");
            give = "";
            for(String s: temp){
                give += capitalizeFirstLetter(s) + " ";
            }
            return give.substring(0, give.length() - 1);
        } else return capitalizeFirstLetter(give);
    }

    /**
     * Nicely formats the name of each given Enum
     * @param e
     * @param replaceUnderscores
     * @return
     */
    public static String enumToString(boolean replaceUnderscores, Enum... e){
        String give = "";
        for(int i = 0; i < e.length; i++){
            give += enumToString(e[i], replaceUnderscores);
            if(i <= e.length - 2){
                give += ", ";
            }
        }
        return give;
    }

    /**
     * Formats a long string into smaller ones. Would be good for item lore
     * @param string
     * @return
     */
    public static List<String> formatLongString(String string) {
        List<String> give = new CopyOnWriteArrayList<>();

        if(string.length() > 50){
            String temp;
            int limit = (string.length() / 50) + (string.length() % 50 > 0 ? 1 : 0);
            for(int i = 0; i < limit; i++){
                if(i < limit - 1) {
                    temp = string.substring(0 + (50 * i), 50 * (i + 1));
                } else {
                    temp = string.substring(0 + (50 * i));
                }
                give.add(temp);
            }
        }

        return give;
    }

    /**
     * Formats a long string into smaller ones suitable for a scoreboard
     * @param string
     * @return
     */
    public static List<String> formatScoreboardString(String string) {
        List<String> give = new CopyOnWriteArrayList<>();
        int maxStringSize = 35;

        if(string.length() > maxStringSize){
            String temp;
            String[] strings = string.split(" ");
            int index = 0;

            while(index < strings.length){
                temp = "";
                while(temp.length() < maxStringSize){
                    if(index <= strings.length - 1){
                        temp += strings[index];
                        temp += " ";
                        index++;
                    } else {
                        break;
                    }
                }
                give.add(temp);
            }
        } else {
            give.add(string);
        }

        return give;
    }
}
