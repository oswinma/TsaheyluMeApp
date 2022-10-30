package me.tsaheylu.serviceImpl;

import io.sentry.spring.tracing.SentrySpan;
import lombok.RequiredArgsConstructor;
import me.tsaheylu.common.Constants;
import me.tsaheylu.common.Texts;
import me.tsaheylu.component.JwtAuthenticationEntryPoint;
import me.tsaheylu.component.JwtUtil;
import me.tsaheylu.exception.OAuth2AuthenticationProcessingException;
import me.tsaheylu.model.LocalUser;
import me.tsaheylu.model.RefreshToken;
import me.tsaheylu.model.User;
import me.tsaheylu.repository.UserRepo;
import me.tsaheylu.security.oauth2.user.OAuth2UserInfo;
import me.tsaheylu.security.oauth2.user.OAuth2UserInfoFactory;
import me.tsaheylu.service.RefreshTokenService;
import me.tsaheylu.service.UserService;
import me.tsaheylu.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepo userRepo;
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);


    @Override
    @SentrySpan
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepo.findByEmail(email);

        return user;
    }

    @Override
    public Map<String, String> checkUser(String emails, String password) {
        // TODO Auto-generated method stub

        String pass = "false";
        String msg = null;
        String id = null;
        String token = null;

        User u = userRepo.findByEmail(emails);
        if (u == null) {
            pass = Constants.RETURN_FAILUTE;
            msg = Texts.MESSAGE_ERROR_EMAILINVALID;
        } else {
            if (u.getStatus() == 0) {
                pass = Constants.RETURN_FAILUTE;
                msg = Texts.MESSAGE_ERROR_EMAILINVALIDATE;
            } else {
                pass = "waiting for validate";
                //        UsernamePasswordAuthenticationToken authenticationToken =
                //            new UsernamePasswordAuthenticationToken(emails, password);
                //
                //        Authentication authentication =
                // authenticationManager.authenticate(authenticationToken);
                //        SecurityContextHolder.getContext().setAuthentication(authentication);
                //        UserDetails userDetails = loadUserByUsername(emails);
                //
                //        token = jwtTokenComponent.generateToken(userDetails);

                //				String passcode = PassEncode.Encode(password);
//        if (userMapper.checkUser(emails, PassEncode.Encode(password)) > 0) {
//          pass = Constants.RETURN_SUCCESS;
//          msg = Texts.MESSAGE_PASS_VALIDATE;
//          id = u.getId().toString();
//        } else {
//          pass = Constants.RETURN_FAILUTE;
//          msg = Texts.MESSAGE_ERROR_WRONGEMAILPASS;
//        }
            }
        }

        Map<String, String> data = new HashMap<String, String>();
        data.put("pass", pass);
        data.put("msg", msg);
        data.put("id", id);
        //    data.put("token", token);

        return data;
    }

    @Override
    public Map<String, String> isEmailValid(String email) {
        // TODO Auto-generated method stub
        String pass = Constants.RETURN_FAILUTE;
        String msg = null;

        if (CommonUtils.checkEmailFormat(email)) {
            User u = userRepo.findByEmail(email);
            ;
            if (u == null) {
                pass = Constants.RETURN_SUCCESS;
                msg = Texts.MESSAGE_PASS_VALIDATE;
            } else {
                pass = Constants.RETURN_FAILUTE;
                msg = Texts.MESSAGE_ERROR_INVALIDEMAIL;
            }

        } else {
            pass = Constants.RETURN_FAILUTE;
            msg = Texts.MESSAGE_ERROR_EMAILFORMAT;
        }

        Map<String, String> data = new HashMap<String, String>();
        data.put("pass", pass);
        data.put("msg", msg);

        return data;
    }

    @Override
    public User getUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (User) principal;
        return user;
    }

    @Override
    public User Get(Long id) {
        return userRepo.findById(id).get();
    }

    @Override
    @Transactional
    public LocalUser processUserRegistration(String registrationId, Map<String, Object> attributes, OidcIdToken idToken, OidcUserInfo userInfo) {
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(registrationId, attributes);
        if (!StringUtils.hasLength(oAuth2UserInfo.getName())) {
            throw new OAuth2AuthenticationProcessingException("Name not found from OAuth2 provider");
        } else if (!StringUtils.hasLength(oAuth2UserInfo.getEmail())) {
            throw new OAuth2AuthenticationProcessingException("Email not found from OAuth2 provider");
        }
        User user = userRepo.findByEmail(oAuth2UserInfo.getEmail());
        if (user != null) {


        }

        return LocalUser.create(user, attributes, idToken, userInfo,oAuth2UserInfo);
    }
}
