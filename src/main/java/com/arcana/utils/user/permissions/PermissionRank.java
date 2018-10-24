package com.arcana.utils.user.permissions;

import com.arcana.utils.ArcanaKey;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Each PermissionRank should only be created once and stored in a Manager<PermissionRank>.
 * These PermissionRanks and Nodes could even be applied to non-traditional uses, such as Guilds or Parties with a
 * hierarchy system.
 */
public class PermissionRank {

    private final ArcanaKey key;
    private final String displayName;

    /**
     * This PermissionRank is bundled with these nodes, meaning when a player is promoted/demoted to this rank,
     * they'll automatically be given these permissions.
     */
    private List<PermissionNode> permissions;

    public PermissionRank(ArcanaKey key, String displayName) {
        this.key = key;
        this.displayName = displayName;
        this.permissions = new CopyOnWriteArrayList<>();
    }

    public PermissionRank(ArcanaKey key, String displayName, PermissionNode... permissions) {
        this.key = key;
        this.displayName = displayName;
        this.permissions = new CopyOnWriteArrayList<>();
        this.permissions.addAll(Arrays.asList(permissions));
    }

    public boolean hasPermission(PermissionNode node){
        return permissions.contains(node);
    }

    public ArcanaKey getKey() {
        return key;
    }

    public String getDisplayName() {
        return displayName;
    }

    public List<PermissionNode> getPermissions() {
        return permissions;
    }
}
