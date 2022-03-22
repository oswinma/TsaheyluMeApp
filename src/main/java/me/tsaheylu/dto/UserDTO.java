package me.tsaheylu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private String id;
    private String nickname;
    private String country;
    private String language;
    private String avatarURL;
    private int status;
    private Date signuptime;

    //  public UserDTO(User u) {
    //    this.avatarURL = u.getAvatarURL();
    //    this.id = String.valueOf(u.getId());
    //    this.email = u.getEmail();
    //    this.nickname = u.getNickname();
    //    this.country = u.getCountry();
    //    this.language = u.getLanguage();
    //    this.status = u.getStatus();
    //    this.signuptime = u.getSignuptime();
    //  }
}
