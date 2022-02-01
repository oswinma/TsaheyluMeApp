package me.tsaheylu.model;

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
public class Group {
//  @Id
//  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;


  private Long fromid;
  private int status = 0;
  private String des;
  private String name;
  private String type;
  private Date createtime;


}
