package me.tsaheylu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.tsaheylu.model.Friend;
import me.tsaheylu.model.User;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvitationDTO {

    private Long id;
    private Long fromid;
    private Long toid;
    private int status;
    private boolean popup;
    private String nickname;
    private String avatarURL;
    private Date bondtime;

    public InvitationDTO(Friend f, User u) {
        this.id = f.getId();
        this.fromid = f.getFromid();
        this.toid = f.getToid();
        this.status = f.getStatus();
        this.setPopup(f.isPopup());
        this.nickname = u.getNickName();
        this.avatarURL = u.getAvatarURL();
        this.bondtime = f.getBondtime();
    }

}
