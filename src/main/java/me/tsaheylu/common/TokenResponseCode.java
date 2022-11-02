package me.tsaheylu.common;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TokenResponseCode {

    VALID,
    INVALID,
    EXPIRED,

}
