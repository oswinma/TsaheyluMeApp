package me.tsaheylu.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;

//@Entity
@Getter
@Setter
public class Comment {

//  @Id
//  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Long fromid;
  private Long toid;
  private Long urlid;
  private Long favurlid;
  private Date sendtime;
  private String content;


}
