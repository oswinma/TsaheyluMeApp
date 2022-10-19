package me.tsaheylu.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum URLInfoStatus {


    VALID(0), Recommendation(1);

    private final int Id;

}
