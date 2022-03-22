package me.tsaheylu.controller;

import lombok.RequiredArgsConstructor;
import me.tsaheylu.DtoMapper.FriendDtoMapper;
import me.tsaheylu.dto.FriendDTO;
import me.tsaheylu.model.Friend;
import me.tsaheylu.model.User;
import me.tsaheylu.service.FriendService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller // This means that this class is a Controller
@RequestMapping(path = "/api/friend") // This means URL's start with /demo (after Application path)
@RequiredArgsConstructor
public class FriendController {

    private final FriendService friendService;
    private final FriendDtoMapper friendDtoMapper;

    @GetMapping(path = "/available")
    public @ResponseBody
    boolean getAvailable() {
        // This returns a JSON or XML with the users
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (User) principal;

        return friendService.hasAvaliableFriends(user.getId());
    }

    @GetMapping(path = "/tsahayluteam")
    public @ResponseBody
    Map<String, Object> getTsahayluteam() {
        // This returns a JSON or XML with the users
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (User) principal;

        return friendService.getTsahayluTeamInfo(user.getId());
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    private FriendDTO create(@RequestBody FriendDTO friendDTO) {
        Friend friend = friendDtoMapper.DtoTo(friendDTO);
        return friendDtoMapper.toDto(friendService.save(friend));
    }

    @PutMapping
    private FriendDTO update(@RequestBody FriendDTO friendDTO) {
        Friend friend = friendDtoMapper.DtoTo(friendDTO);
        return friendDtoMapper.toDto(friendService.update(friend));
    }

    @GetMapping(value = "/{id}")
    private FriendDTO get(@PathVariable Long id) {
        return friendDtoMapper.toDto(friendService.get(id));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void delete(@PathVariable Long id) {
        friendService.delete(id);
    }


    @PostMapping(path = "/invite")
    @ResponseStatus(code = HttpStatus.CREATED)
    private FriendDTO invite(@RequestBody FriendDTO friendDTO) {
        Friend friend = friendDtoMapper.DtoTo(friendDTO);
        return friendDtoMapper.toDto(friendService.invite(friend));
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
