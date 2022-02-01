package me.tsaheylu.model;

import me.tsaheylu.common.URLInfoStatus;
import lombok.Getter;
import lombok.Setter;

//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;


//@Entity
@Getter
@Setter
public class URLInfo {
//  @Id
//  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String url;
  private String title;
  private String icon;
  private String host;
  private int status = URLInfoStatus.VALID;
  private Long share = 0l;
  private Long favs = 0l;


}
