package me.tsaheylu.dto;

public class Contact {

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

  /*	public Contact(
  		String id,
  		String email,
  		String nickname,
  		String avatarURL,
  		Long friendid,
  		Long groupid,
  		Long groupdataid,
  		Long fromid,
  		int status,
  		boolean popup)
  {
  	this.id = id;
  	this.email = email;
  	this.nickname = nickname;
  	this.avatarURL = avatarURL;
  	this.friendid = friendid;
  	this.groupid = groupid;
  	this.groupdataid = groupdataid;
  	this.fromid = fromid;
  	this.status = status;
  	this.popup = popup;
  }

  public Contact(Long id,String email,String nickname,String  avatarURL,Long  friendid,Long fromid,int status,boolean popup)
  {
  	this.id=id.toString();
  	this.email=email;
  	this.nickname=nickname;
  	this.avatarURL=avatarURL;
  	this.friendid=friendid;
  	this.fromid=fromid;
  	this.status=status;
  	this.popup=popup;
  }

  public Contact(String nickname,String email)
  {
  	this.email=email;
  	this.nickname=nickname;
  }

  public Contact(User u)
  {
  	this.id=String.valueOf(u.getId());
  	this.nickname=u.getNickname();
  	this.avatarURL=u.getAvatarURL();
  	this.email=u.getEmail();
  }

  public Contact(User u,Friend f)
  {
  	this.id=String.valueOf(u.getId());
  	this.nickname=u.getNickname();
  	this.avatarURL=u.getAvatarURL();
  	this.friendid=f.getId();
  	this.fromid=f.getFromid();
  	this.status=f.getStatus();
  	this.email=u.getEmail();
  	this.setPopup(f.isPopup());
  }*/

  public Long getGroupid() {
    return groupid;
  }

  public void setGroupid(Long groupid) {
    this.groupid = groupid;
  }

  public Long getGroupdataid() {
    return groupdataid;
  }

  public void setGroupdataid(Long groupdataid) {
    this.groupdataid = groupdataid;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getNickname() {
    return nickname;
  }

  public void setNickname(String nickname) {
    this.nickname = nickname;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getAvatarURL() {
    return avatarURL;
  }

  public void setAvatarURL(String avatarURL) {
    this.avatarURL = avatarURL;
  }

  public Long getFriendid() {
    return friendid;
  }

  public void setFriendid(Long friendid) {
    this.friendid = friendid;
  }

  public Long getFromid() {
    return fromid;
  }

  public void setFromid(Long fromid) {
    this.fromid = fromid;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public boolean isPopup() {
    return popup;
  }

  public void setPopup(boolean popup) {
    this.popup = popup;
  }
}
