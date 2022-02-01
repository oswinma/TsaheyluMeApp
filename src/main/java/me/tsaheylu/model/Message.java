package me.tsaheylu.model;

import me.tsaheylu.common.MessageStatus;
import me.tsaheylu.util.DateUtils;
import lombok.Getter;
import lombok.Setter;

//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
import java.util.Date;


//@Entity
@Getter
@Setter
public class Message {
//  @Id
//  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Long fromid;
  private Long toid;
  private String type;
  private int status = MessageStatus.UNREAD;
  private String content;
  private Date sendtime;
  private Date readtime;
  private Long refid;

  public Message() {}

  public Message(
      final long fromid,
      final long toid,
      final String type,
      final String content,
      final long refid) {
    this.fromid = fromid;
    this.toid = toid;
    this.type = type;
    this.content = content;
    this.sendtime = DateUtils.getCurrentTime();
    this.refid = refid;
  }
}
