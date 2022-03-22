package me.tsaheylu.common;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FriendStatus {

//    public static int INVALID = 0;
//    public static int VALID = 1;
//    public static int BLOCK = 2;

    INVALID(0), VALID(1), BLOCK(2);

    private final int Id;


}
