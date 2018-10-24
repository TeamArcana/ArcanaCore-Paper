package com.arcana.utils.chat.options;

public interface ChannelOption<T> {

    boolean isValid(T parameter);

    ValidateOn validateOn();

    enum ValidateOn{
        ON_MEMBER_JOIN,
        ON_MEMBER_MESSAGE,
        ON_MEMBER_LEAVE;
    }

}
