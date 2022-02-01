package me.tsaheylu.controller;

import me.tsaheylu.common.Constants;
import me.tsaheylu.common.Texts;
import me.tsaheylu.common.response.ResponseResult;
import me.tsaheylu.model.User;
import me.tsaheylu.service.UserService;
import me.tsaheylu.component.JwtTokenComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.security.sasl.AuthenticationException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@RestController // This means that this class is a Controller
@RequestMapping(path = "/api/user") // This means URL's start with /demo (after Application path)
@ResponseResult
public class AuthorizationController {

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

  @Autowired private UserService userService;
  @Autowired private JwtTokenComponent jwtTokenComponent;
  @Autowired private AuthenticationManager authenticationManager;

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
        UserDetails userDetails = userService.loadUserByUsername(email);
        String token = jwtTokenComponent.generateToken(userDetails);

        User user = (User) authentication.getPrincipal();
        data.put("pass", Constants.RETURN_SUCCESS);
        data.put("msg", Texts.MESSAGE_PASS_VALIDATE);
        data.put("id", user.getId().toString());
        data.put("token", token);

      } else {
        data.put("pass", Constants.RETURN_FAILUTE);
        data.put("msg", Texts.MESSAGE_ERROR_WRONGEMAILPASS);
      }
    }
    return data;
  }
}
