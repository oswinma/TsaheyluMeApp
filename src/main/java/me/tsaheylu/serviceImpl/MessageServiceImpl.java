package me.tsaheylu.serviceImpl;

import lombok.RequiredArgsConstructor;
import me.tsaheylu.common.MessageStatus;
import me.tsaheylu.common.MessageType;
import me.tsaheylu.common.Texts;
import me.tsaheylu.dto.MessageDTO;
import me.tsaheylu.model.FavURL;
import me.tsaheylu.model.Message;
import me.tsaheylu.model.User;
import me.tsaheylu.repository.MessageRepo;
import me.tsaheylu.service.MessageService;
import me.tsaheylu.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepo messageRepo;

    private final UserService userService;

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

    @Override
    public MessageDTO save(MessageDTO messageDTO) {
        return null;
    }

    @Override
    public void delete(Long id) {
        messageRepo.deleteById(id);
    }

    @Override
    public MessageDTO get(Long id) {

        Optional<Message> opt = messageRepo.findById(id);
        if (opt.isPresent()) {
            Message message = opt.get();
            return this.toDto(message);
        }

        return null;
    }

    @Override
    public MessageDTO update(MessageDTO messageDTO) {
        return null;
    }

    @Override
    public List<MessageDTO> getMessageDTOListByFromid(Long fromid) {

        return messageRepo.getMessageDTOListByFromid(fromid, null);
    }

    @Override
    public List<MessageDTO> getUnreadMessageDTOListByFromid(Long fromid) {

        return messageRepo.getMessageDTOListByFromid(fromid, MessageStatus.UNREAD.getId());
    }

    MessageDTO toDto(Message message) {
        return messageRepo.getDtoById(message.getId());
    }

    List<MessageDTO> toDtoList(List<Message> messageList) {
        return messageRepo.getDtoListById(messageList.stream().map(Message::getId).collect(Collectors.toList()));
    }


    @Override
    public Message buildFavurlSendMessage(FavURL fu) {

        Long fromid = fu.getFromid();
        Long toid = fu.getToid();
        User u = userService.Get(fromid);
        Message m = new Message();
        m.setFromid(fromid);
        m.setToid(toid);
        m.setType(MessageType.FAVURL.getType());
        m.setContent(u.getNickname() + Texts.MESSAGE_CONTENT_FAVURL);
        m.setSendtime(fu.getSendtime());
        m.setRefid(fu.getId());
        return m;
    }

    @Override
    public void saveAll(List<Message> mlist) {
        messageRepo.saveAll(mlist);
    }
}
