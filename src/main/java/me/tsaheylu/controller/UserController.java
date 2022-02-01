package me.tsaheylu.controller;

import me.tsaheylu.common.response.ResponseResult;
import me.tsaheylu.model.User;
import me.tsaheylu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.security.sasl.AuthenticationException;
import java.util.HashMap;
import java.util.Map;

@RestController // This means that this class is a Controller
@RequestMapping(path = "/api/user") // This means URL's start with /demo (after Application path)
@ResponseResult
public class UserController {

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
    @Value("${file.upload.path}")
    private String uploadFilePath;

    @Autowired
    private UserService userService;

    @PostMapping(value = "/emailcheck")
//  @CrossOrigin(origins = "*", maxAge = 3600)
    public Map<String, String> emailCheck(@RequestParam String email) throws AuthenticationException {

        return userService.isEmailValid(email);
    }

    @GetMapping(value = "/basic")
//  @CrossOrigin(origins = "*", maxAge = 3600)
    public User getUser() throws AuthenticationException {
        return userService.getUser();
    }

    @PostMapping(value = "/avatar/update")
//  @CrossOrigin(origins = "*", maxAge = 3600)
    public Map<String, String> updateAvatar(@RequestPart MultipartFile file) {


//            file.
        Map<String, String> data = new HashMap<String, String>();
        String avatarURL = "";
        data.put("avatarURL", avatarURL);

        return data;
    }

}
