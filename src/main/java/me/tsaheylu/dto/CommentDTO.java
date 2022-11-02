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
public class CommentDTO {

    private Long id;
    private Long fromid;
    private Long toid;
    private Long urlid;
    private Long favurlid;
    private Date sendtime;
    private String content;
    private String nickname;
    private String tonickname;
    private String avatarURL;


    public CommentDTO(Comment com, User u, User tu) {
        this.id = com.getId();
        this.fromid = com.getFromid();
        this.toid = com.getToid();
        this.urlid = com.getUrlid();
        this.favurlid = com.getFavurlid();
        this.sendtime = com.getSendtime();
        this.content = com.getContent();
        this.nickname = u.getNickName();
        this.avatarURL = u.getAvatarURL();
        this.tonickname = tu.getNickName();
    }

    public CommentDTO(Comment com, User u) {
        this.id = com.getId();
        this.fromid = com.getFromid();
        this.toid = com.getToid();
        this.urlid = com.getUrlid();
        this.favurlid = com.getFavurlid();
        this.sendtime = com.getSendtime();
        this.content = com.getContent();
        this.nickname = u.getNickName();
        this.avatarURL = u.getAvatarURL();
    }
}
