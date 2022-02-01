package me.tsaheylu.service.impl;

import me.tsaheylu.dao.mapper.MessageMapper;
import me.tsaheylu.model.User;
import me.tsaheylu.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

  @Autowired private MessageMapper messageMapper;

  @Override
  public Integer getUnreadNum() {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    User user = (User) principal;
    return messageMapper.getUnReadMsgNum(user.getId());
  }
}
