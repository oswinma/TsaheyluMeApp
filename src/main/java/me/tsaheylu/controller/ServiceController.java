package me.tsaheylu.controller;

import io.sentry.Sentry;
import lombok.RequiredArgsConstructor;
import me.tsaheylu.common.Constants;
import me.tsaheylu.common.Texts;
import me.tsaheylu.common.response.ResponseResult;
import me.tsaheylu.component.JwtTokenComponent;
import me.tsaheylu.model.User;
import me.tsaheylu.service.PushChannelService;
import me.tsaheylu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.security.sasl.AuthenticationException;
import java.util.Map;

@RestController // This means that this class is a Controller
@RequestMapping(path = "/api/services") // This means URL's start with /demo (after Application path)
@RequiredArgsConstructor
public class ServiceController {

    private final PushChannelService pushChannelService;

    @GetMapping(value = "/channel")
    public String setupUserChannel() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (User) principal;
        return pushChannelService.setupChannel(user.getId());

    }
}
