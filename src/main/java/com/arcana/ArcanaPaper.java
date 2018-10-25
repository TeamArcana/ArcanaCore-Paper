package com.arcana;

import com.arcana.utils.manager.ArcanaItemRegistry;
import com.arcana.utils.manager.ArcanaPagedInvRegistry;
import org.bukkit.plugin.java.JavaPlugin;

public final class ArcanaPaper extends JavaPlugin {

    public static ArcanaPaper INSTANCE;

    private ArcanaItemRegistry itemRegistry;
    private ArcanaPagedInvRegistry pagedInvRegistry;

    @Override
    public void onEnable() {
        INSTANCE = this;

        itemRegistry = new ArcanaItemRegistry();
        pagedInvRegistry =  new ArcanaPagedInvRegistry();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public ArcanaItemRegistry getItemRegistry() {
        return itemRegistry;
    }

    public ArcanaPagedInvRegistry getPagedInvRegistry() {
        return pagedInvRegistry;
    }
}
