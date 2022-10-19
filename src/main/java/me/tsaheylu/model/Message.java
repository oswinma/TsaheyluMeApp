package me.tsaheylu.model;

import lombok.*;
import me.tsaheylu.common.MessageStatus;
import me.tsaheylu.util.DateUtils;

//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
import javax.persistence.*;
import java.util.Date;


//@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "fromid")
    private Long fromid;
    @Column(name = "toid")
    private Long toid;
    @Column(name = "type")
    private String type;

    @Column(name = "status")
    private int status = MessageStatus.UNREAD.getId();
    @Column(name = "content")
    private String content;
    @Column(name = "sendtime")
    private Date sendtime;

    @Column(name = "readtime")
    private Date readtime;
    @Column(name = "refid")
    private Long refid;


    public Message(final long fromid, final long toid, final String type, final String content, final long refid) {
        this.fromid = fromid;
        this.toid = toid;
        this.type = type;
        this.content = content;
        this.sendtime = DateUtils.getCurrentTime();
        this.refid = refid;
    }
}
