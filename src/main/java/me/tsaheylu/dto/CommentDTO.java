package me.tsaheylu.dto;

import java.util.Date;

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

  public String getTonickname() {
    return tonickname;
  }

  public void setTonickname(String tonickname) {
    this.tonickname = tonickname;
  }

  public Long getFavurlid() {
    return favurlid;
  }

  public void setFavurlid(Long favurlid) {
    this.favurlid = favurlid;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getFromid() {
    return fromid;
  }

  public void setFromid(Long fromid) {
    this.fromid = fromid;
  }

  public Long getToid() {
    return toid;
  }

  public void setToid(Long toid) {
    this.toid = toid;
  }

  public Long getUrlid() {
    return urlid;
  }

  public void setUrlid(Long urlid) {
    this.urlid = urlid;
  }

  public Date getSendtime() {
    return sendtime;
  }

  public void setSendtime(Date sendtime) {
    this.sendtime = sendtime;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public String getAvatarURL() {
    return avatarURL;
  }

  public void setAvatarURL(String avatarURL) {
    this.avatarURL = avatarURL;
  }
}
