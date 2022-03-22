package me.tsaheylu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


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
}
