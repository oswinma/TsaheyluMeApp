package me.tsaheylu.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MessageStatus {

    UNREAD(0), READ(1), DELETED(2);

    private final int Id;
}
