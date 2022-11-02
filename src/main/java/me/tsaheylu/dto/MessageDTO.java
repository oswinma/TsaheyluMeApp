package me.tsaheylu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.tsaheylu.common.Constants;
import me.tsaheylu.common.MessageType;
import me.tsaheylu.model.Message;
import me.tsaheylu.model.User;

import java.util.Date;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageDTO {
    private Long id;
    private Long fromid;
    private Long toid;
    private String type;
    private int status;
    private String content;
    private String nickname;
    private String jump_link;
    private String avatarURL;
    private Date sendtime;
    private Date readtime;
    private Long refid;

    public MessageDTO(Message m, User u) {
        this.id = m.getId();
        this.fromid = m.getFromid();
        this.toid = m.getToid();
        this.type = m.getType();
        this.content = m.getContent();
        this.status = m.getStatus();
        this.nickname = u.getNickName();
        this.avatarURL = u.getAvatarURL();
        this.sendtime = m.getSendtime();
        this.readtime = m.getReadtime();
        this.refid = m.getRefid();

        if (this.type.equals(MessageType.FAVURL.getType())) {
            this.jump_link = Constants.PAGE_HOME;
        } else if (this.type.equals(MessageType.FRIEND.getType())) {
            this.jump_link = Constants.PAGE_FRIENDS;
        } else if (this.type.equals(MessageType.INVITATION.getType())) {
            this.jump_link = Constants.PAGE_INVITATION;
        }
    }

}
