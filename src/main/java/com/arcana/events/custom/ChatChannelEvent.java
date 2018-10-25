package com.arcana.events.custom;

import com.arcana.utils.chat.channel.ChatChannel;
import com.arcana.utils.chat.channel.ChatChannelUser;
import org.bukkit.event.Cancellable;

public abstract class ChatChannelEvent extends ArcanaEvent {

    private ChatChannel chatChannel;
    private ChatChannelUser user;

    public ChatChannelEvent(ChatChannel chatChannel, ChatChannelUser user) {
        this.chatChannel = chatChannel;
        this.user = user;
    }

    public ChatChannelEvent(String cause, ChatChannel chatChannel, ChatChannelUser user) {
        super(cause);
        this.chatChannel = chatChannel;
        this.user = user;
    }

    public ChatChannel getChatChannel() {
        return chatChannel;
    }

    public ChatChannelUser getUser() {
        return user;
    }

    public static class PlayerJoined extends ChatChannelEvent implements Cancellable{

        private boolean cancelled = false;

        public PlayerJoined(ChatChannel chatChannel, ChatChannelUser user) {
            super(chatChannel, user);
        }

        public PlayerJoined(String cause, ChatChannel chatChannel, ChatChannelUser user) {
            super(cause, chatChannel, user);
        }

        @Override
        public boolean isCancelled() {
            return cancelled;
        }

        @Override
        public void setCancelled(boolean cancel) {
            this.cancelled = cancel;
        }
    }

    public static class PlayerLeft extends ChatChannelEvent{

        public PlayerLeft(ChatChannel chatChannel, ChatChannelUser user) {
            super(chatChannel, user);
        }

        public PlayerLeft(String cause, ChatChannel chatChannel, ChatChannelUser user) {
            super(cause, chatChannel, user);
        }
    }

    public static class Mute extends ChatChannelEvent implements Cancellable{

        private boolean cancelled = false;

        public Mute(ChatChannel chatChannel, ChatChannelUser user) {
            super(chatChannel, user);
        }

        public Mute(String cause, ChatChannel chatChannel, ChatChannelUser user) {
            super(cause, chatChannel, user);
        }

        @Override
        public boolean isCancelled() {
            return cancelled;
        }

        @Override
        public void setCancelled(boolean cancel) {
            this.cancelled = cancel;
        }
    }

    public static class UnMute extends ChatChannelEvent{

        public UnMute(ChatChannel chatChannel, ChatChannelUser user) {
            super(chatChannel, user);
        }

        public UnMute(String cause, ChatChannel chatChannel, ChatChannelUser user) {
            super(cause, chatChannel, user);
        }
    }

    public static class SendMessage extends ChatChannelEvent implements Cancellable {

        private boolean cancelled = false;

        private String message;

        public SendMessage(ChatChannel chatChannel, ChatChannelUser user, String message) {
            super(chatChannel, user);
            this.message = message;
        }

        public SendMessage(String cause, ChatChannel chatChannel, ChatChannelUser user, String message) {
            super(cause, chatChannel, user);
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        @Override
        public boolean isCancelled() {
            return cancelled;
        }

        @Override
        public void setCancelled(boolean cancel) {
            this.cancelled = cancel;
        }
    }
}
