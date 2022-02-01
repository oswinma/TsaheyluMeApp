package me.tsaheylu.model;

import me.tsaheylu.common.FriendStatus;
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
public class Friend {


//  @Id
//  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Long fromid;
  private Long toid;
  private int status = FriendStatus.INVALID;
  private boolean popup = true;
  private Date bondtime;

}
