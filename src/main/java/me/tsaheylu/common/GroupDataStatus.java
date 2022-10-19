package me.tsaheylu.common;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum GroupDataStatus {

    INVALID(0), VALID(1);

    private final int Id;

}
