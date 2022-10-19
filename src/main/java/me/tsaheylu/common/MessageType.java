package me.tsaheylu.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MessageType {

    FRIEND("FRIEND"), FAVURL("FAVURL"), INVITATION("INVITATION"), COMMENT("COMMENT");

    private String type;

}
