package me.tsaheylu.serviceImpl;

import io.sentry.spring.tracing.SentrySpan;
import lombok.RequiredArgsConstructor;
import me.tsaheylu.apiRequest.ResetPasswordRequest;
import me.tsaheylu.apiRequest.SignUpRequest;
import me.tsaheylu.apiRequest.UpdatePasswordRequest;
import me.tsaheylu.common.Constants;
import me.tsaheylu.common.Texts;
import me.tsaheylu.common.UserStatus;
import me.tsaheylu.exception.OAuth2AuthenticationProcessingException;
import me.tsaheylu.exception.TokenInvalidException;
import me.tsaheylu.exception.UserAlreadyExistAuthenticationException;
import me.tsaheylu.exception.UserNotFoundException;
import me.tsaheylu.model.LocalUser;
import me.tsaheylu.model.RefreshToken;
import me.tsaheylu.model.User;
import me.tsaheylu.repository.UserRepo;
import me.tsaheylu.security.oauth2.user.OAuth2UserInfo;
import me.tsaheylu.security.oauth2.user.OAuth2UserInfoFactory;
import me.tsaheylu.service.EmailService;
import me.tsaheylu.service.UserService;
import me.tsaheylu.util.CommonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {


    private final UserRepo userRepo;
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final PasswordEncoder passwordEncoder;

    private final EmailService emailService;

    private final RefreshTokenServiceImpl refreshTokenService;

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

        return LocalUser.create(user, attributes, idToken, userInfo, oAuth2UserInfo);
    }

    @Override
    public User registerNewUser(SignUpRequest signUpRequest) throws UserAlreadyExistAuthenticationException {
        String email = signUpRequest.getEmail();
        if (userRepo.findByEmail(email) != null) {
            throw new UserAlreadyExistAuthenticationException("User with email id " + signUpRequest.getEmail() + " already exist");
        }

        User user = new User();
        Date now = Calendar.getInstance().getTime();
        user.setEmail(email);
        user.setNickName(signUpRequest.getNickName());
        user.setCreatedTime(now);
        user.setLastModifiedTime(now);
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user = userRepo.save(user);

        emailService.sendEmailVerificationEmail(user);
        return user;
    }

    @Override
    @Transactional
    public void verifyToken(String token) throws TokenInvalidException {

        RefreshToken refreshToken = refreshTokenService.findByToken(token).orElseThrow(() -> new TokenInvalidException(token, "token not found"));
        refreshTokenService.verifyExpiration(refreshToken);

        User user = refreshToken.getUser();
        if (user == null) {
            throw new TokenInvalidException(token, "User not found with token");
        }
    }

    @Override
    @Transactional
    public void verifyEmail(String token) throws TokenInvalidException {

        RefreshToken refreshToken = refreshTokenService.findByToken(token).orElseThrow(() -> new TokenInvalidException(token, "token not found"));
        refreshTokenService.verifyExpiration(refreshToken);

        User user = refreshToken.getUser();
        if (user == null) {
            throw new TokenInvalidException(token, "User not found with token");
        }
        user.setStatus(UserStatus.VALID.getId());
        userRepo.save(user);
        refreshTokenService.delete(refreshToken);
    }

    @Override
    public void resendToken(String existingVerificationToken) throws TokenInvalidException {

        Optional<RefreshToken> optionalRefreshToken = refreshTokenService.findByToken(existingVerificationToken);
        if (optionalRefreshToken.isPresent()) {
            RefreshToken refreshToken = optionalRefreshToken.get();
            refreshToken = refreshTokenService.updateToken(refreshToken);
            emailService.sendEmailVerificationEmail(refreshToken.getUser());
        } else {
            throw new TokenInvalidException(existingVerificationToken, "token not found");
        }
    }

    @Override
    public void resetPassword(ResetPasswordRequest resetPasswordRequest) throws UserNotFoundException {
        String email = resetPasswordRequest.getEmail();
        User user = userRepo.findByEmail(email);
        if (user == null) {
            throw new UserNotFoundException(email, "User not found with email");
        } else {
            emailService.sendResetPasswordEmail(user);
        }

    }

    @Override
    @Transactional
    public void updatePassword(UpdatePasswordRequest updatePasswordRequest) throws TokenInvalidException {

        String token = updatePasswordRequest.getToken();
        String password = updatePasswordRequest.getPassword();
        RefreshToken refreshToken = refreshTokenService.findByToken(token).orElseThrow(() -> new TokenInvalidException(token, "token not found"));
        refreshTokenService.verifyExpiration(refreshToken);

        User user = refreshToken.getUser();
        if (user == null) {
            throw new TokenInvalidException(token, "User not found with token");
        } else {
            user.setPassword(passwordEncoder.encode(updatePasswordRequest.getPassword()));
            userRepo.save(user);
            refreshTokenService.delete(refreshToken);
        }
    }
}
