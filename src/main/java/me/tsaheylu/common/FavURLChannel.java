package me.tsaheylu.common;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum FavURLChannel {

//    public static final String WEB = "WEB";
//    public static final String CHROME= "CHROME";

    WEB("WEB"), CHROME("CHROME");

    private String type;

}
