package me.tsaheylu.security.oauth2;


import java.io.IOException;
import java.net.URI;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.RequiredArgsConstructor;
import me.tsaheylu.component.JwtUtil;
import me.tsaheylu.model.LocalUser;
import me.tsaheylu.model.RefreshToken;
import me.tsaheylu.model.User;
import me.tsaheylu.security.oauth2.user.OAuth2UserInfo;
import me.tsaheylu.service.RefreshTokenService;
import me.tsaheylu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;


@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private final JwtUtil jwtUtil;
    private final RefreshTokenService refreshTokenService;
    private final UserService userService;

    @Value("${tsahayluMe.oauth2.authorizedRedirectUris}")
    private String redirectUrl;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {

        String targetUrl = determineTargetUrl(request, response, authentication);

        if (response.isCommitted()) {
            logger.debug("Response has already been committed. Unable to redirect to " + targetUrl);
            return;
        }

        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }

    @Override
    protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

        String targetUrl = redirectUrl;
        LocalUser localUser = (LocalUser) authentication.getPrincipal();

        User user = localUser.getUser();
        OAuth2UserInfo oAuth2UserInfo = localUser.getoAuth2UserInfo();

        if (user == null) {

            targetUrl = UriComponentsBuilder.fromUriString(redirectUrl + "/signup")
                    .queryParam("email", oAuth2UserInfo.getEmail())
                    .queryParam("nickName", oAuth2UserInfo.getName())
                    .queryParam("avatarURL", oAuth2UserInfo.getImageUrl())
                    .build().toUriString();

        } else {
            UserDetails userDetails = user;
            String accessToken = jwtUtil.generateToken(userDetails);
            RefreshToken refreshToken = refreshTokenService.createRefreshToken(user);

            targetUrl = UriComponentsBuilder.fromUriString(redirectUrl + "/login").queryParam("accessToken", accessToken).queryParam("refreshToken", refreshToken.getToken()).build().toUriString();
        }

        logger.info("targetUrl: " + targetUrl);

        return targetUrl;
    }


}