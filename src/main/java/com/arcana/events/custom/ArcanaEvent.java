package com.arcana.events.custom;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public abstract class ArcanaEvent extends Event {

    public static void callEvent(ArcanaEvent event){
        Bukkit.getPluginManager().callEvent(event);
    }

    public static final String SERVER_CAUSE = "Server";
    private String cause;
    private static final HandlerList handlers = new HandlerList();

    public ArcanaEvent(){this(SERVER_CAUSE);}

    public ArcanaEvent(String cause){
        this.cause = cause;
    }

    public String getCause() {
        return cause;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList(){return handlers;}
}
