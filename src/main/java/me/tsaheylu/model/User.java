package me.tsaheylu.model;

import me.tsaheylu.common.UserStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

// import javax.persistence.Entity;
// import javax.persistence.GeneratedValue;
// import javax.persistence.GenerationType;
// import javax.persistence.Id;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

// @Entity
@Getter
@Setter
public class User implements UserDetails, Serializable {
  //  @Id
  //  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String email;
  private String password;
  private String nickname;
  private String country;
  private String language;
  private String avatarURL;
  private String code;
  private int status = UserStatus.INVALID;
  private Date signuptime;
  private boolean favurlSubscription = true;
  private boolean emailSubscription = true;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
