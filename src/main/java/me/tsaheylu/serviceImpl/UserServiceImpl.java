package me.tsaheylu.serviceImpl;

import io.sentry.spring.tracing.SentrySpan;
import lombok.RequiredArgsConstructor;
import me.tsaheylu.common.Constants;
import me.tsaheylu.common.Texts;
import me.tsaheylu.component.JwtUtil;
import me.tsaheylu.model.CustomOAuth2User;
import me.tsaheylu.model.RefreshToken;
import me.tsaheylu.model.User;
import me.tsaheylu.repository.UserRepo;
import me.tsaheylu.service.RefreshTokenService;
import me.tsaheylu.service.UserService;
import me.tsaheylu.util.CommonUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final JwtUtil jwtUtil;
    private final RefreshTokenService refreshTokenService;
    private final UserRepo userRepo;

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
    public void processOAuthLogin(CustomOAuth2User oAuth2User, HttpServletResponse response) {


        User user = userRepo.findByEmail(oAuth2User.getEmail());
        if (user == null) {
            user = new User();
            user.setEmail(oAuth2User.getEmail());
            user.setStatus(1);
            userRepo.save(user);
            try {
                response.sendRedirect("/signup");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            UserDetails userDetails = user;
            String accessToken = jwtUtil.generateToken(userDetails);
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(user);
            try {
                response.sendRedirect("/mylist/new?accessToken=" + accessToken + "&refreshToken=" + refreshToken.getToken());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }
}
