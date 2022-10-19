package me.tsaheylu.common;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.parameters.P;

@Getter
@AllArgsConstructor
public enum FavURLStatus {

    PENDING(0), NEW(1), ARCHIVE(2), REMOVE(3);

    private final int Id;


}
