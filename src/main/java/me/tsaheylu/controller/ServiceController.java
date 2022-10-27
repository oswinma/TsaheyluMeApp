package me.tsaheylu.controller;

import lombok.RequiredArgsConstructor;
import me.tsaheylu.model.User;
import me.tsaheylu.service.PushChannelService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController // This means that this class is a Controller
@RequestMapping(path = "/api/services") // This means URL's start with /demo (after Application path)
@RequiredArgsConstructor
public class ServiceController {

    private final PushChannelService pushChannelService;

    @GetMapping(value = "/channel")
    public Map<String, String> setupUserChannel() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (User) principal;
        return pushChannelService.setupChannel(user.getId());
    }
}
