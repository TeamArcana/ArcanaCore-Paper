package com.arcana.utils.chat.options;

import com.arcana.utils.user.ArcanaPermissions;

public class PermissionLocked implements ChannelOption<ArcanaPermissions> {

    private final String permission;

    public PermissionLocked(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }

    @Override
    public boolean isValid(ArcanaPermissions parameter) {
        return parameter.hasPermission(permission);
    }

    @Override
    public ValidateOn validateOn() {
        return ValidateOn.ON_MEMBER_JOIN;
    }
}
