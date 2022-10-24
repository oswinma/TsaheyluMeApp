package me.tsaheylu.controller;

import lombok.RequiredArgsConstructor;
import me.tsaheylu.common.response.ResponseResult;
import me.tsaheylu.dto.InvitationDTO;
import me.tsaheylu.dto.MessageDTO;
import me.tsaheylu.model.User;
import me.tsaheylu.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.security.sasl.AuthenticationException;
import java.util.List;

@RestController // This means that this class is a Controller
@RequestMapping(path = "/api/messages") // This means URL's start with /demo (after Application path)
@ResponseResult
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    //    /api/message
///api/message/remove
    @GetMapping(value = "/unreadnum")
    public Integer getUnreadNum() throws AuthenticationException {
        return messageService.getUnreadNum();
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    private MessageDTO create(@RequestBody MessageDTO messageDTO) {
        return messageService.save(messageDTO);
    }

    @PutMapping
    private MessageDTO update(@RequestBody MessageDTO messageDTO) {
        return messageService.save(messageDTO);
    }

    @GetMapping(value = "/{id}")
    private MessageDTO get(@PathVariable Long id) {

        return messageService.get(id);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void delete(@PathVariable Long id) {
        messageService.delete(id);
    }


    @GetMapping
    public List<MessageDTO> getMessageDTOList() {
        // This returns a JSON or XML with the users
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (User) principal;

        return messageService.getMessageDTOListByFromid(user.getId());
    }

    @GetMapping(value = "/unread")
    public List<MessageDTO> getUnreadMessageDTOList() {
        // This returns a JSON or XML with the users
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (User) principal;

        return messageService.getUnreadMessageDTOListByFromid(user.getId());
    }

}
