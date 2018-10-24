package com.arcana.events.custom;

import com.arcana.utils.chat.ChatChannel;
import com.arcana.utils.chat.ChatChannelUser;
import org.bukkit.event.Cancellable;

public abstract class ChatChannelEvent extends ArcanaEvent {

    private ChatChannel chatChannel;

    public ChatChannelEvent(ChatChannel chatChannel) {
        this.chatChannel = chatChannel;
    }

    public ChatChannelEvent(String cause, ChatChannel chatChannel) {
        super(cause);
        this.chatChannel = chatChannel;
    }

    public ChatChannel getChatChannel() {
        return chatChannel;
    }

    public static class PlayerJoined extends ChatChannelEvent implements Cancellable{

        private boolean cancelled = false;

        private ChatChannelUser user;

        public PlayerJoined(ChatChannel chatChannel, ChatChannelUser user) {
            super(chatChannel);
            this.user = user;
        }

        public PlayerJoined(String cause, ChatChannel chatChannel, ChatChannelUser user) {
            super(cause, chatChannel);
            this.user = user;
        }

        public ChatChannelUser getUser() {
            return user;
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
        private ChatChannelUser user;

        public PlayerLeft(ChatChannel chatChannel, ChatChannelUser user) {
            super(chatChannel);
            this.user = user;
        }

        public PlayerLeft(String cause, ChatChannel chatChannel, ChatChannelUser user) {
            super(cause, chatChannel);
            this.user = user;
        }

        public ChatChannelUser getUser() {
            return user;
        }
    }

    public static class SendMessage extends ChatChannelEvent implements Cancellable {

        private boolean cancelled = false;

        private ChatChannelUser sender;
        private String message;

        public SendMessage(ChatChannel chatChannel, ChatChannelUser sender, String message) {
            super(chatChannel);
            this.sender = sender;
            this.message = message;
        }

        public SendMessage(String cause, ChatChannel chatChannel, ChatChannelUser sender, String message) {
            super(cause, chatChannel);
            this.sender = sender;
            this.message = message;
        }

        public ChatChannelUser getSender() {
            return sender;
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
