package me.tsaheylu.model;

import me.tsaheylu.common.GroupDataStatus;
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
public class GroupData {
//  @Id
//  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Long toid;
  private Long groupid;
  private int status = GroupDataStatus.INVALID;
  private Date addtime;


}
