package me.tsaheylu.controller;

import me.tsaheylu.model.User;
import me.tsaheylu.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller // This means that this class is a Controller
@RequestMapping(path = "/api/friend") // This means URL's start with /demo (after Application path)
public class FriendController {

  @Autowired FriendService friendService;

  @GetMapping(path = "/available")
  public @ResponseBody boolean getAvailable() {
    // This returns a JSON or XML with the users
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    User user = (User) principal;

    return friendService.hasAvaliableFriends(user.getId());
  }

  @GetMapping(path = "/tsahayluteam")
  public @ResponseBody Map<String, Object> getTsahayluteam() {
    // This returns a JSON or XML with the users
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    User user = (User) principal;

    return friendService.getTsahayluTeamInfo(user.getId());
  }

  /// api/friend
  /// api/friend/accept
  /// api/friend/available
  /// api/friend/block
  /// api/friend/delete
  /// api/friend/info
  /// api/friend/invitation
  /// api/friend/invite
  /// api/friend/inviteemail
  /// api/friend/invitereturn
  /// api/friend/ismyfriend
  /// api/friend/popup
  /// api/friend/reject
  /// api/friend/tsahayluteam

}
