package me.tsaheylu.common;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TokenType {

    AUTH(0), VERIFY(1);

    private final int Id;
}
