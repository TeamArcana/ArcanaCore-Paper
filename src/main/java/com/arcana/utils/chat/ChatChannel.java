package com.arcana.utils.chat;

import com.arcana.utils.chat.options.ChannelOption;
import com.arcana.utils.text.Messager;
import org.bukkit.Bukkit;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class ChatChannel {

    private List<ChatChannelUser> members;
    private Optional<Messager.Prefix> prefix;

    /**
     * An option is extensible and can check for certain criteria to be validated before a certain action happens.
     */
    private Optional<ChannelOption> option;

    public ChatChannel() {
        this(Optional.empty());
    }

    public ChatChannel(Optional<Messager.Prefix> prefix) {
        this(prefix, Optional.empty());
    }

    public ChatChannel(Optional<Messager.Prefix> prefix, Optional<ChannelOption> option) {
        this.prefix = prefix;
        this.option = option;
        members = new CopyOnWriteArrayList<>();
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

    public void addMember(ChatChannelUser user){
        if(!isMember(user)){
            if(option.isPresent() && option.get().validateOn() == ChannelOption.ValidateOn.ON_MEMBER_JOIN){
                if(!isOptionValid(user)){
                    return;
                }
            }
            members.add(user);
            sendJoinMessage(user);
        }
    }

    public void remove(ChatChannelUser user){
        if(members.contains(user)){
            members.remove(user);

            if(option.isPresent() && option.get().validateOn() == ChannelOption.ValidateOn.ON_MEMBER_LEAVE){
                if(!isOptionValid(user)){
                    return;
                }
            }
            sendLeaveMessage(user);
        }
    }

    /**
     * This should only be called from the ChatChannelUser class. Grab the instance of that class
     * that relates to the player sending a message and have the message sent there.
     * @param message
     * @param sender
     */
    public void sendMessage(String message, ChatChannelUser sender){
        if(option.isPresent() && option.get().validateOn() == ChannelOption.ValidateOn.ON_MEMBER_MESSAGE){
            if(!isOptionValid(sender)){
                return;
            }
        }

        for(ChatChannelUser user: members){
            if(prefix.isPresent()){
                Messager.sendMessage(Bukkit.getPlayer(user.getOwner()), message, prefix);
            } else {
                Messager.sendMessage(Bukkit.getPlayer(user.getOwner()), message);
            }
        }
    }
}
