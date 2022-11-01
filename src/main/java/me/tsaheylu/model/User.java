package me.tsaheylu.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.tsaheylu.common.UserStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User implements UserDetails, Serializable {
    //  @Id
    //  @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "country")
    private String country;

    @Column(name = "language")
    private String language;

    @Column(name = "avatarURL")
    private String avatarURL;

    @Column(name = "status")
    private int status = UserStatus.INVALID.getId();

    @Column(name = "createdTime")
    private Date createdTime;

    @Column(name = "lastModifiedTime")
    private Date lastModifiedTime;

    @Column(name = "favurlSubscription")
    private boolean favurlSubscription = true;

    @Column(name = "emailSubscription")
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
