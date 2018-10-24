package com.arcana.utils.user;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

public class ArcanaPermissions {

    private UUID owner;
    private List<String> permissions;

    public ArcanaPermissions(UUID owner) {
        this.owner = owner;
        permissions = new CopyOnWriteArrayList<>();
    }

    public boolean hasPermission(String permission){
        return permissions.contains(permission);
    }

    public void addPermission(String p){
        if(!permissions.contains(p)){
            permissions.add(p);
        }
    }

    public void removePermissions(String p){
        permissions.remove(p);
    }
}
