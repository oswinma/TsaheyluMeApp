package me.tsaheylu.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MessageType {

//    public static final String FRIEND= "FRIEND";
//    public static final String FAVURL= "FAVURL";
//    public static final String INVITATION = "INVITATION";
//    public static final String COMMENT = "COMMENT";

    FRIEND("FRIEND"), FAVURL("FAVURL"), INVITATION("INVITATION"), COMMENT("COMMENT");

    private String type;

}
