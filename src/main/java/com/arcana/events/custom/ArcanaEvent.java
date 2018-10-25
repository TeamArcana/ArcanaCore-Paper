package com.arcana.events.custom;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/**
 * This is the abstract class for a custom event.
 */
public abstract class ArcanaEvent extends Event {

    /**
     * A simple way to call an event.
     * @param event
     */
    public static void callEvent(ArcanaEvent event){
        Bukkit.getPluginManager().callEvent(event);
    }

    public static final String SERVER_CAUSE = "Server";

    /**
     * This is the reason for why the event was fired. If you don't want to set this each time,
     * the static final String SERVER_CAUSE can be used.
     */
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
