package me.tsaheylu.model;



import me.tsaheylu.common.FavURLStatus;
import lombok.Getter;
import lombok.Setter;
//
//import javax.persistence.Entity;
//import javax.persistence.Id;
import java.util.Date;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;

//@Entity
@Getter
@Setter
public class FavURL {

//  @Id
//  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Long fromid;
  private Long toid;
  private Long urlid;
  private int status = FavURLStatus.PENDING;
  private int fstatus = FavURLStatus.ARCHIVE;
  private Date sendtime;
  private Long serial;
  private Date readtime;
  private String comment;
  private String channel;
  private boolean fav = false;


}
