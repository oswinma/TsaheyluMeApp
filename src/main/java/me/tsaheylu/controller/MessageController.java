package me.tsaheylu.controller;

import me.tsaheylu.common.response.ResponseResult;
import me.tsaheylu.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.security.sasl.AuthenticationException;

@RestController // This means that this class is a Controller
@RequestMapping(path = "/api/message") // This means URL's start with /demo (after Application path)
@ResponseResult
public class MessageController {

    @Autowired
    private MessageService messageService;
//    /api/message
///api/message/remove
///api/message/unreadnum
@GetMapping(value = "/unreadnum")
public Integer getUnreadNum() throws AuthenticationException {
    return messageService.getUnreadNum();
}


}
