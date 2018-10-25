package com.arcana.utils.chat;

import org.bukkit.ChatColor;

import java.util.Optional;

/**
 * This is a convenient way to hold formatting for messages. These could be used for automatic server messages,
 * party messages, players, etc.
 */
public class ChatMessageFormat {

    public static Builder builder(){return new Builder();}

    private Optional<ChatColor> prefixColor, nameColor, messageColor, messageStyle;

    protected ChatMessageFormat(Optional<ChatColor> prefixColor, Optional<ChatColor> nameColor, Optional<ChatColor> messageColor, Optional<ChatColor> messageStyle) {
        this.prefixColor = prefixColor;
        this.nameColor = nameColor;
        this.messageColor = messageColor;
        this.messageStyle = messageStyle;
    }

    public String applyFormat(Optional<String> prefix, Optional<String> name, Optional<String> message){
        String give = "";

        if(prefix.isPresent()){
            if(prefixColor.isPresent()){
                give += prefixColor.get();
            }
            give += prefix.get() + " ";
        }

        if(name.isPresent()){
            if(nameColor.isPresent()){
                give += nameColor.get();
            }
            give += name.get() + " ";
        }

        if(message.isPresent()){
            if(messageColor.isPresent()){
                give += messageColor.get();
            }
            if(messageStyle.isPresent()){
                give += messageStyle.get();
            }
            give += message.get() + " ";
        }

        return give;
    }

    public Optional<ChatColor> getPrefixColor() {
        return prefixColor;
    }

    public Optional<ChatColor> getNameColor() {
        return nameColor;
    }

    public Optional<ChatColor> getMessageColor() {
        return messageColor;
    }

    public Optional<ChatColor> getMessageStyle() {
        return messageStyle;
    }

    public static class Builder {

        private Optional<ChatColor> prefixColor = Optional.empty(), nameColor = Optional.empty(),
                messageColor = Optional.empty(), messageStye = Optional.empty();

        public Builder prefixColor(ChatColor c){
            this.prefixColor = Optional.of(c);
            return this;
        }

        public Builder nameColor(ChatColor c){
            this.nameColor = Optional.of(c);
            return this;
        }

        public Builder messageColor(ChatColor c){
            this.messageColor = Optional.of(c);
            return this;
        }

        public Builder messageStyle(ChatColor c){
            this.prefixColor = Optional.of(c);
            return this;
        }

        public ChatMessageFormat build(){
            return new ChatMessageFormat(prefixColor, nameColor, messageColor, messageStye);
        }

    }
}
