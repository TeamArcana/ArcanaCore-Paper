package com.arcana.utils.user.permissions;

/**
 * Each PermissionNode should only be created once and should only exist within PermissionRanks, preferably,
 * or stored in a Manager<PermissionNode> if absolutely necessary. These PermissionNodes and Ranks could even
 * be applied to non-traditional uses, such as Guilds or Parties with a hierarchy system.
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
