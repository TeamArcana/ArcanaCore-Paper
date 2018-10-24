package com.arcana.utils.chat;

import com.arcana.utils.UUIDUtils;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArrayList;

public class ChatChannelUser {

    private UUID owner;

    /**
     * Subscribed channels are all the channels a player is 'listening' to. The player will receive
     * messages from all channels that are subscribed to.
     */
    private List<ChatChannel> subscribed;

    /**
     * The current channel is the channel that the player will send a message to if typing in chat. A setup
     * like this allows for the player to listen to multiple chats at once while messaging any particular one.
     */
    private ChatChannel current;

    public ChatChannelUser(UUID owner) {
        this.owner = owner;
        subscribed = new CopyOnWriteArrayList<>();
    }

    public void setCurrent(ChatChannel current) {
        this.current = current;
    }

    public boolean isOwner(UUID uuid) {
        return UUIDUtils.equals(owner, uuid);
    }

    public List<ChatChannel> getSubscribed() {
        return subscribed;
    }

    public ChatChannel getCurrent() {
        return current;
    }

    public UUID getOwner() {
        return owner;
    }

    public void sendMessage(String s){
        current.sendMessage(s, this);
    }
}
