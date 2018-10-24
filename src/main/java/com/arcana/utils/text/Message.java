package com.arcana.utils.text;

import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

public class Message {

    public static MessageBuilder builder(){return new MessageBuilder();}

    private List<Player> receivers;
    private List<String> strings;
    private Optional<Messager.Prefix> prefix;

    protected Message(List<Player> receivers, List<String> strings, Optional<Messager.Prefix> prefix) {
        this.receivers = receivers;
        this.strings = strings;
        this.prefix = prefix;

        if(prefix.isPresent()) {
            for (String s : strings) {
                s = prefix.get().getText() + s;
            }
        }
    }

    public List<Player> getReceivers() {
        return receivers;
    }

    public List<String> getStrings() {
        return strings;
    }

    public Optional<Messager.Prefix> getPrefix() {
        return prefix;
    }

    public static class MessageBuilder {
        private List<Player> receivers;
        private List<String> strings;
        private Optional<Messager.Prefix> prefix;

        public MessageBuilder(){
            receivers = new CopyOnWriteArrayList<>();
            strings = new CopyOnWriteArrayList<>();
            prefix = Optional.empty();
        }

        public MessageBuilder addViewer(Player player){
            if(!receivers.contains(player)){
                receivers.add(player);
            }
            return this;
        }

        public MessageBuilder addViewers(Player... players){
            return addViewers(Arrays.asList(players));
        }

        public MessageBuilder addViewers(List<Player> players){
            receivers.addAll(players);
            return this;
        }

        public MessageBuilder addMessage(String text){
            strings.add(text);
            return this;
        }

        public MessageBuilder addParentChildren(String parent, String bulletPoint, String... children){
            strings.add(parent);
            for(String s: children){
                strings.add(" " + bulletPoint + " " + s);
            }
            return this;
        }

        public MessageBuilder setPrefix(Messager.Prefix prefix){
            this.prefix = Optional.of(prefix);
            return this;
        }

        public Message build(){
            return new Message(receivers, strings, prefix);
        }
    }

}
