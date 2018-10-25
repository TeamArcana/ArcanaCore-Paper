package com.arcana.utils.user;

import com.arcana.utils.chat.channel.ChatChannelUser;
import com.arcana.utils.economy.Account;
import com.arcana.utils.scoreboard.ArcanaScoreboard;
import com.arcana.utils.user.permissions.ArcanaPermissions;

import java.util.UUID;

public abstract class PlayerBase {

    private UUID owner;
    private ChatChannelUser chatChannelUser;
    private Account account;
    private ArcanaScoreboard scoreboard;
    private ArcanaPermissions permissions;

    public PlayerBase(UUID owner, ChatChannelUser chatChannelUser, Account account, ArcanaScoreboard scoreboard, ArcanaPermissions permissions) {
        this.owner = owner;
        this.chatChannelUser = chatChannelUser;
        this.account = account;
        this.scoreboard = scoreboard;
        this.permissions = permissions;
    }

    public UUID getOwner() {
        return owner;
    }

    public ChatChannelUser getChatChannelUser() {
        return chatChannelUser;
    }

    public Account getAccount() {
        return account;
    }

    public ArcanaScoreboard getScoreboard() {
        return scoreboard;
    }

    public ArcanaPermissions getPermissions() {
        return permissions;
    }
}
