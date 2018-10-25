package com.arcana.utils.chat.channel;

import com.arcana.utils.UUIDUtils;
import com.arcana.utils.chat.ChatMessageFormat;
import com.arcana.utils.user.PlayerBase;

import java.util.List;
import java.util.Optional;
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

    /**
     * This is automatic formatting for messages. This format could correlate to this Player's rank, for instance.
     */
    private Optional<ChatMessageFormat> messaageFormatting;

    public ChatChannelUser(UUID owner) {
        this(owner, Optional.empty());
    }

    public ChatChannelUser(UUID owner, Optional<ChatMessageFormat> messaageFormatting) {
        this.owner = owner;
        subscribed = new CopyOnWriteArrayList<>();
    }

    public Optional<ChatMessageFormat> getMessaageFormatting() {
        return messaageFormatting;
    }

    public void subscribe(ChatChannel chatChannel){
        if(!isSubscribed(chatChannel)){
            subscribed.add(chatChannel);
            chatChannel.addMember(this);
        }
    }

    public void leave(ChatChannel chatChannel){
        if(isSubscribed(chatChannel)){
            subscribed.remove(chatChannel);
            chatChannel.remove(this);
        }
    }

    public void mute(ChatChannel chatChannel){
        if(isSubscribed(chatChannel)){
            chatChannel.muteFor(this);
        }
    }

    public void unmute(ChatChannel chatChannel){
        if(isSubscribed(chatChannel)){
            chatChannel.unmuteFor(this);
        }
    }

    public boolean isSubscribed(ChatChannel chatChannel){
        return subscribed.contains(chatChannel);
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

    public void sendMessage(String s, PlayerBase sender){
        current.sendMessage(s, sender);
    }
}
