package me.tsaheylu.service;

// import com.github.scribejava.core.model.OAuth1AccessToken;
// import com.github.scribejava.core.model.OAuth2AccessToken;
// import com.github.scribejava.core.oauth.OAuth10aService;
// import com.github.scribejava.core.oauth.OAuth20Service;
import me.tsaheylu.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface UserService extends UserDetailsService {

  Map<String, String> checkUser(String emails, String password);

  Map<String, String> isEmailValid(String email);

  User getUser();

  User Get(Long id);

  OidcUser processUserRegistration(String registrationId, Map<String, Object> attributes, OidcIdToken idToken, OidcUserInfo userInfo);


  //  public abstract String getAvatar(String id);
  //
  //  public abstract UserDTO conUserToUserDTO(User u);
  //
  //  public abstract OAuth20Service getGoogleOAuth20Service(String returnType);
  //
  //  public abstract OAuth10aService getTwitterOAuth20Service(String returnType);
  //
  //  public abstract OAuth20Service getFacebookOAuth20Service(String returnType);
  //
  //  public abstract OAuth2AccessToken getGoogleAccessToken(
  //      OAuth20Service gservice, HttpServletRequest req, HttpServletResponse resp)
  //      throws InterruptedException, ExecutionException, IOException;
  //
  //  public abstract OAuth1AccessToken getTwitterAccessToken(
  //      OAuth10aService tservice, HttpServletRequest req, HttpServletResponse resp)
  //      throws IOException, ExecutionException, InterruptedException;
  //
  //  public abstract OAuth2AccessToken getFacebookAccessToken(
  //      OAuth20Service fservice, HttpServletRequest req, HttpServletResponse resp)
  //      throws InterruptedException, ExecutionException, IOException;
  //
  //  public abstract HashMap<String, String> getGoogleOAuthUserInfo(
  //      OAuth2AccessToken accessToken, OAuth20Service service, String query)
  //      throws InterruptedException, ExecutionException, IOException;
  //
  //  public abstract HashMap<String, String> getFacebookOAuthUserInfo(
  //      OAuth2AccessToken accessToken, OAuth20Service fservice)
  //      throws InterruptedException, ExecutionException, IOException;
  //
  //  public abstract HashMap<String, String> getTwitterOAuthUserInfo(
  //      OAuth1AccessToken accessToken, OAuth10aService tservice)
  //      throws InterruptedException, ExecutionException, IOException;
  //
  //  public abstract int openIdAuthorization(
  //      HttpServletRequest req,
  //      HttpServletResponse resp,
  //      HashMap<String, String> oauthUserInfo,
  //      boolean isGetContact);
  //
  //  public abstract Long getUserid(HttpServletRequest req, HttpServletResponse resp);
  //
  //  public abstract UserOpenID attachOpenIDToExistUser(
  //      User u,
  //      String openid_url,
  //      String provider_id,
  //      String openid_provider,
  //      String refresh_token,
  //      int expires_in);
  //
  //  public abstract OAuth2AccessToken getValidAccssToken(
  //      UserOpenID useropenid, OAuth20Service gservice);
  //
  //  public abstract void createNewUser(User nu);
  //
}
