package me.tsaheylu.model;

import lombok.Getter;
import lombok.Setter;

// import javax.persistence.Entity;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
// import javax.persistence.Id;
import java.util.Date;

//@Entity
@Getter
@Setter
public class UserOpenID {
//  @Id
//  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String token;
  private String refresh_token;
  private Long user_id;
  private String provider_id;
  private String openid_provider;
  private Date attachtime;
  private int expires_in;
}
