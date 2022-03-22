package me.tsaheylu.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MsgNumDTO {

    private Long toid;
    private Long num = 0l;

    public MsgNumDTO(Long toid) {
        this.toid = toid;
    }
}
