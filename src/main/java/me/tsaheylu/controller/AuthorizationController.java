package me.tsaheylu.controller;

import io.sentry.Sentry;
import lombok.RequiredArgsConstructor;
import me.tsaheylu.common.Constants;
import me.tsaheylu.common.Texts;
import me.tsaheylu.common.response.ResponseResult;
import me.tsaheylu.common.response.TokenRefreshResponse;
import me.tsaheylu.component.JwtAuthenticationEntryPoint;
import me.tsaheylu.component.JwtUtil;
import me.tsaheylu.exception.TokenRefreshException;
import me.tsaheylu.model.RefreshToken;
import me.tsaheylu.model.User;
import me.tsaheylu.request.RefreshTokenRequest;
import me.tsaheylu.service.RefreshTokenService;
import me.tsaheylu.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Map<String, String> login(@RequestParam String email, @RequestParam String password)
            throws AuthenticationException {

        Map<String, String> data = userService.checkUser(email, password);

        if (data.get("pass") == "waiting for validate") {

            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(email, password);

            Authentication authentication = authenticationManager.authenticate(authenticationToken);

            if (authentication.isAuthenticated()) {
                SecurityContextHolder.getContext().setAuthentication(authentication);
                User user = (User) authentication.getPrincipal();
                UserDetails userDetails = userService.loadUserByUsername(email);
                String token = jwtUtil.generateToken(userDetails);
                RefreshToken refreshToken = refreshTokenService.createRefreshToken(user);

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

        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = jwtUtil.generateToken(user);
                    logger.debug("new accessToken", token);
                    return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
                })
                .orElseThrow(() -> new TokenRefreshException(requestRefreshToken,
                        "Refresh token is not in database!"));
    }

    @PostMapping(value = "/emailcheck")
//  @CrossOrigin(origins = "*", maxAge = 3600)
    public Map<String, String> emailCheck(@RequestParam String email) throws AuthenticationException {

        return userService.isEmailValid(email);
    }
}
