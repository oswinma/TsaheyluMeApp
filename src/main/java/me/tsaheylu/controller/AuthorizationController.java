package me.tsaheylu.controller;

import io.sentry.Sentry;
import lombok.RequiredArgsConstructor;
import me.tsaheylu.apiRequest.RefreshTokenRequest;
import me.tsaheylu.apiRequest.SignUpRequest;
import me.tsaheylu.apiResponse.DefaultResponse;
import me.tsaheylu.apiResponse.TokenRefreshResponse;
import me.tsaheylu.common.Constants;
import me.tsaheylu.common.Texts;
import me.tsaheylu.common.TokenType;
import me.tsaheylu.component.JwtUtil;
import me.tsaheylu.exception.TokenExpiredException;
import me.tsaheylu.exception.TokenInvalidException;
import me.tsaheylu.exception.UserAlreadyExistAuthenticationException;
import me.tsaheylu.model.RefreshToken;
import me.tsaheylu.model.User;
import me.tsaheylu.service.RefreshTokenService;
import me.tsaheylu.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.security.sasl.AuthenticationException;
import javax.validation.Valid;
import java.util.Map;

@RestController // This means that this class is a Controller
@RequestMapping(path = "/api/auth") // This means URL's start with /demo (after Application path)
@RequiredArgsConstructor
public class AuthorizationController {

    private static final Logger logger = LoggerFactory.getLogger(AuthorizationController.class);

    /// api/user/basic
    /// api/user/avatar/update
    /// api/user/check
    /// api/user/confirmemail
    /// api/user/create
    /// api/user/emailcheck
    /// api/user/forgot
    /// api/user/invitesignup
    /// api/user/page
    /// api/user/pass
    /// api/user/search
    /// api/user/sendcode
    /// api/user/update

    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshTokenService;

    @PostMapping(value = "/check")
//  @CrossOrigin(origins = "*", maxAge = 3600)
    public Map<String, String> login(@RequestParam String email, @RequestParam String password) throws AuthenticationException {

        Map<String, String> data = userService.checkUser(email, password);

        if (data.get("pass") == "waiting for validate") {

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);

            Authentication authentication = authenticationManager.authenticate(authenticationToken);

            if (authentication.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
                User user = (User) authentication.getPrincipal();
                UserDetails userDetails = userService.loadUserByUsername(email);
                String token = jwtUtil.generateToken(userDetails);
                RefreshToken refreshToken = refreshTokenService.createRefreshToken(user, TokenType.AUTH);

                data.put("pass", Constants.RETURN_SUCCESS);
                data.put("msg", Texts.MESSAGE_PASS_VALIDATE);
                data.put("id", user.getId().toString());
                data.put("accessToken", token);
                data.put("refreshToken", refreshToken.getToken());

            } else {
                data.put("pass", Constants.RETURN_FAILUTE);
                data.put("msg", Texts.MESSAGE_ERROR_WRONGEMAILPASS);
            }
        }

        try {
            throw new Exception("This is a test.");
        } catch (Exception e) {
            Sentry.captureException(e);
        }

        return data;
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshtoken(@Valid @RequestBody RefreshTokenRequest request) {
        String requestRefreshToken = request.getRefreshToken();

        return refreshTokenService.findByToken(requestRefreshToken).map(refreshTokenService::verifyExpiration).map(RefreshToken::getUser).map(user -> {
            String token = jwtUtil.generateToken(user);
            logger.debug("new accessToken", token);
            return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
        }).orElseThrow(() -> new TokenInvalidException(requestRefreshToken, "Refresh token is not in database!"));
    }

    @PostMapping(value = "/emailcheck")
//  @CrossOrigin(origins = "*", maxAge = 3600)
    public Map<String, String> emailCheck(@RequestParam String email) throws AuthenticationException {

        return userService.isEmailValid(email);
    }


    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        try {
            userService.registerNewUser(signUpRequest);
        } catch (UserAlreadyExistAuthenticationException e) {
            logger.error("Exception Ocurred", e);
            return new ResponseEntity<>(new DefaultResponse(false, "Email Address already in use!"), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok().body(new DefaultResponse(true, "User registered successfully"));

    }


    @PostMapping(value = "/verify")
//  @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseEntity<?> verifyToken(@RequestParam String token) throws AuthenticationException {

        try {
            userService.verifyToken(token);
        } catch (TokenInvalidException e) {
            logger.error("Exception Ocurred", e);
            return new ResponseEntity<>(new DefaultResponse(false, "TokenInvalidException"), HttpStatus.BAD_REQUEST);
        } catch (TokenExpiredException e) {
            logger.error("Exception Ocurred", e);
            return new ResponseEntity<>(new DefaultResponse(false, "TokenExpiredException"), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok().body(new DefaultResponse(true, "email verified successfully"));

    }

    @PostMapping(value = "/resend")
//  @CrossOrigin(origins = "*", maxAge = 3600)
    public ResponseEntity<?> resendToken(@RequestParam String token) throws AuthenticationException {

        try {
            userService.resendToken(token);
        } catch (TokenInvalidException e) {
            logger.error("Exception Ocurred", e);
            return new ResponseEntity<>(new DefaultResponse(false, "EmailVefifyException"), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok().body(new DefaultResponse(true, "new token sent successfully"));

    }
}
