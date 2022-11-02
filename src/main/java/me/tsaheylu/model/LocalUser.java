package me.tsaheylu.model;

import me.tsaheylu.security.oauth2.user.OAuth2UserInfo;
import me.tsaheylu.util.CommonUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;


public class LocalUser extends User implements OAuth2User, OidcUser {

    /**
     *
     */
    private static final long serialVersionUID = -2845160792248762779L;
    private final OidcIdToken idToken;
    private final OidcUserInfo userInfo;
    private Map<String, Object> attributes;

    private me.tsaheylu.model.User user;

    private OAuth2UserInfo oAuth2UserInfo;

    public LocalUser(final String userID, final String password, final boolean enabled, final boolean accountNonExpired, final boolean credentialsNonExpired, final boolean accountNonLocked, final Collection<? extends GrantedAuthority> authorities, final me.tsaheylu.model.User user) {
        this(userID, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities, user, null, null);
    }

    public LocalUser(final String userID, final String password, final boolean enabled, final boolean accountNonExpired, final boolean credentialsNonExpired, final boolean accountNonLocked, final Collection<? extends GrantedAuthority> authorities, final me.tsaheylu.model.User user, OidcIdToken idToken, OidcUserInfo userInfo) {
        super(userID, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.user = user;
        this.idToken = idToken;
        this.userInfo = userInfo;
    }

    public static LocalUser create(me.tsaheylu.model.User user, Map<String, Object> attributes, OidcIdToken idToken, OidcUserInfo userInfo, OAuth2UserInfo oAuth2UserInfo) {

        LocalUser localUser = new LocalUser(user.getEmail(), user.getPassword(), user.isEnabled(), true, true, true, CommonUtils.buildSimpleGrantedAuthorities("user"), user, idToken, userInfo);
        localUser.setAttributes(attributes);
        localUser.setoAuth2UserInfo(oAuth2UserInfo);
        return localUser;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    public void setUser(me.tsaheylu.model.User user) {
        this.user = user;
    }

    public OAuth2UserInfo getoAuth2UserInfo() {
        return oAuth2UserInfo;
    }

    public void setoAuth2UserInfo(OAuth2UserInfo oAuth2UserInfo) {
        this.oAuth2UserInfo = oAuth2UserInfo;
    }

    @Override
    public String getName() {
        return this.user.getNickName();
    }

    @Override
    public Map<String, Object> getAttributes() {
        return this.attributes;
    }

    @Override
    public Map<String, Object> getClaims() {
        return this.attributes;
    }

    @Override
    public OidcUserInfo getUserInfo() {
        return this.userInfo;
    }

    @Override
    public OidcIdToken getIdToken() {
        return this.idToken;
    }

    public me.tsaheylu.model.User getUser() {
        return user;
    }
}
