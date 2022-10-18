package me.tsaheylu.controller;

import lombok.RequiredArgsConstructor;
import me.tsaheylu.dto.ContactDTO;
import me.tsaheylu.dto.FriendDTO;
import me.tsaheylu.dto.InvitationDTO;
import me.tsaheylu.model.Friend;
import me.tsaheylu.model.User;
import me.tsaheylu.service.FriendService;
import me.tsaheylu.service.InvitationService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/api/invitations")
@RequiredArgsConstructor
public class InvitationController {
    private final InvitationService invitationService;


    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    private InvitationDTO create(@RequestBody InvitationDTO invitationDTO) {
        return invitationService.save(invitationDTO);
    }

    @PutMapping
    private InvitationDTO update(@RequestBody InvitationDTO invitationDTO) {
        return invitationService.save(invitationDTO);
    }

    @GetMapping(value = "/{id}")
    private InvitationDTO get(@PathVariable Long id) {

        return invitationService.get(id);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void delete(@PathVariable Long id) {
        invitationService.delete(id);
    }


    @GetMapping
    public List<InvitationDTO> getInvitationDTOList() {
        // This returns a JSON or XML with the users
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (User) principal;

        return invitationService.getInvitationDTOList(user.getId());
    }

}
