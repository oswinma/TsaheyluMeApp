package me.tsaheylu.serviceImpl;

import me.tsaheylu.dao.mapper.MessageDaoMapper;
import me.tsaheylu.model.User;
import me.tsaheylu.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {

  @Autowired private MessageDaoMapper messageMapper;

  @Override
  public Integer getUnreadNum() {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    User user = (User) principal;
    return messageMapper.getUnReadMsgNum(user.getId());
  }
}
