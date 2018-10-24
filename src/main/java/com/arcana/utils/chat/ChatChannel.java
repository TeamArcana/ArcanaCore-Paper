package com.arcana.utils.chat;

import com.arcana.events.custom.ArcanaEvent;
import com.arcana.events.custom.ChatChannelEvent;
import com.arcana.utils.text.Messager;
import org.bukkit.Bukkit;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class ChatChannel {

    private List<ChatChannelUser> members;
    private Optional<Messager.Prefix> prefix;
    private List<ChatChannelUser> mutedMembers;

    public ChatChannel() {
        this(Optional.empty());
    }

    public ChatChannel(Optional<Messager.Prefix> prefix) {
        this.prefix = prefix;
        members = new CopyOnWriteArrayList<>();
        mutedMembers = new CopyOnWriteArrayList<>();
    }

    /**
     * Here you should do any extra work you need to to validate the ChannelOption with the given user
     * @param user
     * @return
     */
    protected abstract boolean isOptionValid(ChatChannelUser user);

    protected abstract void sendJoinMessage(ChatChannelUser user);
    protected abstract void sendLeaveMessage(ChatChannelUser user);

    public Optional<Messager.Prefix> getPrefix() {
        return prefix;
    }

    public boolean isMember(ChatChannelUser user){
        return members.contains(user);
    }

    /**
     * This should only be called from the ChatChannelUser class.
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
     * This should only be called from the ChatChannelUser class.
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
     * This should only be called from the ChatChannelUser class.
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
     * This should only be called from the ChatChannelUser class.
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
     * This should only be called from the ChatChannelUser class. Grab the instance of that class
     * that relates to the player sending a message and have the message sent there.
     * @param message
     * @param sender
     */
    public void sendMessage(String message, ChatChannelUser sender){

        ChatChannelEvent.SendMessage event = new ChatChannelEvent.SendMessage(this, sender, message);
        ArcanaEvent.callEvent(event);

        if(!event.isCancelled()) {
            for (ChatChannelUser user : members) {
                if(mutedMembers.contains(user)){
                    continue;
                }

                if (prefix.isPresent()) {
                    Messager.sendMessage(Bukkit.getPlayer(user.getOwner()), message, prefix);
                } else {
                    Messager.sendMessage(Bukkit.getPlayer(user.getOwner()), message);
                }
            }
        }
    }
}
