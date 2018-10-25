package com.arcana.utils.chat.channel;

import com.arcana.events.custom.ArcanaEvent;
import com.arcana.events.custom.ChatChannelEvent;
import com.arcana.utils.chat.ChatMessageFormat;
import com.arcana.utils.chat.message.Messager;
import com.arcana.utils.user.PlayerBase;
import org.bukkit.Bukkit;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * A ChatChannel is what dictates what messages get sent to what players, here, its members. Players can
 * subscribe to multiple channels at once to receive the messages from each, regardless of which channel
 * they're currently typing in.
 */
public abstract class ChatChannel {

    private List<ChatChannelUser> members;
    private Optional<Messager.Prefix> prefix;
    private List<ChatChannelUser> mutedMembers;
    private Optional<ChatMessageFormat> channelMessageFormat;

    public ChatChannel() {
        this(Optional.empty(), Optional.empty());
    }

    public ChatChannel(Optional<Messager.Prefix> prefix, Optional<ChatMessageFormat> channelMessageFormat) {
        this.prefix = prefix;
        this.channelMessageFormat = channelMessageFormat;
        members = new CopyOnWriteArrayList<>();
        mutedMembers = new CopyOnWriteArrayList<>();
    }

    /**
     * Here you should do any extra work you need to to validate the ChannelOption with the given user
     * @param user
     * @return
     */
    protected abstract boolean isOptionValid(ChatChannelUser user);

    /**
     * Be sure to apply the channelMessageFormat to each message sent by the channel (not by players)
     * @param user
     */
    protected abstract void sendJoinMessage(ChatChannelUser user);
    protected abstract void sendLeaveMessage(ChatChannelUser user);

    protected Optional<ChatMessageFormat> getChannelMessageFormat(){
        return channelMessageFormat;
    }

    public Optional<Messager.Prefix> getPrefix() {
        return prefix;
    }

    public boolean isMember(ChatChannelUser user){
        return members.contains(user);
    }

    /**
     * This should only be called from the PlayerBase class.
     * @param user
     */
    public void addMember(ChatChannelUser user){
        if(!isMember(user)){
            ChatChannelEvent.PlayerJoined event = new ChatChannelEvent.PlayerJoined(this, user);
            ArcanaEvent.callEvent(event);

            if(!event.isCancelled()){
                members.add(user);
                sendJoinMessage(user);
            }
        }
    }

    /**
     * This should only be called from the PlayerBase class.
     * @param user
     */
    public void remove(ChatChannelUser user){
        if(members.contains(user)){
            members.remove(user);
            mutedMembers.remove(user);
            sendLeaveMessage(user);

            ChatChannelEvent.PlayerLeft event = new ChatChannelEvent.PlayerLeft(this, user);
            ArcanaEvent.callEvent(event);
        }
    }

    /**
     * This should only be called from the PlayerBase class.
     * @param user
     */
    public void muteFor(ChatChannelUser user){
        if(isMember(user)){
            if(!isMutedFor(user)){

                ChatChannelEvent.Mute event = new ChatChannelEvent.Mute(this, user);
                ArcanaEvent.callEvent(event);

                if(!event.isCancelled()) {
                    mutedMembers.add(user);
                }
            }
        }
    }

    /**
     * This should only be called from the PlayerBase class.
     * @param user
     */
    public void unmuteFor(ChatChannelUser user){
        if(isMutedFor(user)){
            mutedMembers.remove(user);

            ChatChannelEvent.UnMute event = new ChatChannelEvent.UnMute(this, user);
            ArcanaEvent.callEvent(event);
        }
    }

    public boolean isMutedFor(ChatChannelUser user){
        return mutedMembers.contains(user);
    }

    /**
     * This should only be called from the PlayerBase class. Grab the instance of that class
     * that relates to the player sending a message and have the message sent there.
     * @param message
     * @param sender
     */
    public void sendMessage(String message, PlayerBase sender){

        ChatChannelUser user = sender.getChatChannelUser();

        ChatChannelEvent.SendMessage event = new ChatChannelEvent.SendMessage(this, user, message);
        ArcanaEvent.callEvent(event);

        if(!event.isCancelled()) {

            for (ChatChannelUser c : members) {
                if(mutedMembers.contains(c)){
                    continue;
                }

                if (prefix.isPresent()) {
                    Messager.sendMessage(Bukkit.getPlayer(c.getOwner()), message, prefix);
                } else {
                    Messager.sendMessage(Bukkit.getPlayer(c.getOwner()), message);
                }
            }
        }
    }
}
