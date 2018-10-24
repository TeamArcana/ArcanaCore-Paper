package com.arcana.utils.user.permissions;

/**
 * Each PermissionNode should only be created once and stored in a Manager<PermissionNode>.
 * These PermissionNodes and Ranks could even be applied to non-traditional uses, such as Guilds or Parties with a
 * hierarchy system.
 */
public class PermissionNode {

    private final String permission;

    public PermissionNode(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
