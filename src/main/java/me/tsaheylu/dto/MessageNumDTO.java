package me.tsaheylu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.tsaheylu.model.Comment;
import me.tsaheylu.model.User;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MessageNumDTO {

    private Long toid;

    private Long num = 0l;


}
