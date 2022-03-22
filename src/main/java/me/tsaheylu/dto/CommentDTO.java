package me.tsaheylu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

  /*	public CommentDTO(
  		Long id,
  		Long fromid,
  		Long toid,
  		Long urlid,
  		Long favurlid,
  		Date sendtime,
  		String content,
  		String nickname,
  		String tonickname,
  		String avatarURL )
  {
  	this.id = id;
  	this.fromid = fromid;
  	this.toid = toid;
  	this.urlid = urlid;
  	this.favurlid = favurlid;
  	this.sendtime = sendtime;
  	this.content = content;
  	this.nickname = nickname;
  	this.tonickname = tonickname;
  	this.avatarURL = avatarURL;
  }

  public CommentDTO(Comment com,User u,User tu)
  {
  	this.id=com.getId();
  	this.fromid=com.getFromid();
  	this.toid=com.getToid();
  	this.urlid=com.getUrlid();
  	this.favurlid=com.getFavurlid();
  	this.sendtime=com.getSendtime();
  	this.content=com.getContent();
  	this.nickname=u.getNickname();
  	this.avatarURL=u.getAvatarURL();
  	this.tonickname=tu.getNickname();
  }

  public CommentDTO(Comment com,User u)
  {
  	this.id=com.getId();
  	this.fromid=com.getFromid();
  	this.toid=com.getToid();
  	this.urlid=com.getUrlid();
  	this.favurlid=com.getFavurlid();
  	this.sendtime=com.getSendtime();
  	this.content=com.getContent();
  	this.nickname=u.getNickname();
  	this.avatarURL=u.getAvatarURL();
  }*/
}
