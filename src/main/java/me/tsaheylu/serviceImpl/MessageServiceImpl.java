package me.tsaheylu.serviceImpl;

import lombok.RequiredArgsConstructor;
import me.tsaheylu.dao.mapper.MessageDaoMapper;
import me.tsaheylu.dto.MessageDTO;
import me.tsaheylu.model.Message;
import me.tsaheylu.model.User;
import me.tsaheylu.repository.FriendRepo;
import me.tsaheylu.repository.MessageRepo;
import me.tsaheylu.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepo messageRepo;

    @Override
    public Integer getUnreadNum() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (User) principal;
        Integer unreadNum = messageRepo.getUnReadMsgNum(user.getId());
        return unreadNum;
    }

    @Override
    public MessageDTO createMessage(Message message) {
        messageRepo.save(message);
//    messageservice.updateMsgNumToChannel(m.getToid());

        return null;
    }
}
