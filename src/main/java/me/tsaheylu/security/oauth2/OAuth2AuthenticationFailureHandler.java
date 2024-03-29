package me.tsaheylu.security.oauth2;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;


@Component
public class OAuth2AuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {


    @Value("${tsahayluMe.oauth2.authorizedRedirectUris}")
    private String redirectUrl;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {



        String targetUrl = UriComponentsBuilder.fromUriString(redirectUrl + "/error").queryParam("error", exception.getLocalizedMessage()).build().toUriString();

        getRedirectStrategy().sendRedirect(request, response, targetUrl);
    }
}