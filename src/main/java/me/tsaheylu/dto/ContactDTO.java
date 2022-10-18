package me.tsaheylu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.tsaheylu.model.Friend;
import me.tsaheylu.model.User;

import java.sql.Timestamp;
import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContactDTO {
    private String id;
    private String email;
    private String nickname;
    private String avatarURL;
    private Long friendid;
    private Long groupid;
    private Long groupdataid;
    private Long fromid;
    private int status;
    private boolean popup;

    private Long toid;
    private Date bondtime;


    public ContactDTO(String nickname, String email) {
        this.email = email;
        this.nickname = nickname;
    }

    public ContactDTO(User u) {
        this.id = String.valueOf(u.getId());
        this.nickname = u.getNickname();
        this.avatarURL = u.getAvatarURL();
        this.email = u.getEmail();
    }

    public ContactDTO(User u, Friend f) {
        this.id = String.valueOf(u.getId());
        this.nickname = u.getNickname();
        this.avatarURL = u.getAvatarURL();
        this.friendid = f.getId();
        this.fromid = f.getFromid();
        this.status = f.getStatus();
        this.email = u.getEmail();
        this.popup = f.isPopup();
        this.toid = f.getToid();
        this.bondtime = f.getBondtime();
    }
}
