package me.tsaheylu.serviceImpl;

import lombok.RequiredArgsConstructor;
import me.tsaheylu.DtoMapper.MessageDtoMapper;
import me.tsaheylu.common.Constants;
import me.tsaheylu.common.MessageStatus;
import me.tsaheylu.common.MessageType;
import me.tsaheylu.common.Texts;
import me.tsaheylu.dto.FavURLDTO;
import me.tsaheylu.dto.MessageDTO;
import me.tsaheylu.dto.MessageNumDTO;
import me.tsaheylu.model.FavURL;
import me.tsaheylu.model.Message;
import me.tsaheylu.model.User;
import me.tsaheylu.repository.MessageRepo;
import me.tsaheylu.service.MessageService;
import me.tsaheylu.service.PushChannelService;
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

    private final PushChannelService pushChannelService;

    private final MessageDtoMapper messageDtoMapper;

    @Override
    public Integer getUnreadNum() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = (User) principal;
        Integer unreadNum = messageRepo.getUnReadMsgNum(user.getId());
        return unreadNum;
    }

    @Override
    public MessageDTO createMessage(Message message) {
        message = messageRepo.save(message);
        updateMsgNumToChannel(message);
        return null;
    }

    @Override
    public MessageDTO save(MessageDTO messageDTO) {
        Message message = messageDtoMapper.DtoTo(messageDTO);
        message = messageRepo.save(message);
        return toDto(message);
    }

    @Override
    public Message saveEntity(Message message) {
        message = messageRepo.save(message);
        return message;
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
    public void saveAll(List<Message> mlist) {
        messageRepo.saveAll(mlist);
    }


    @Override
    public void updateMsgNumToChannel(Message message) {

        Long toid = message.getToid();
        int num = messageRepo.getUnReadMsgNum(toid);
        MessageNumDTO messageNumDTO = new MessageNumDTO(toid, num);
        pushChannelService.sendMsgNumToChannel(messageNumDTO);
    }

    @Override
    public Message buildFavurlReadMessage(FavURLDTO favURLDTO) {

        Long fromid = favURLDTO.getToid();
        Long toid = favURLDTO.getFromid();
        if (toid == Constants.TsahayluTeamID) {
            return null;
        }
        Message rm = new Message();
        rm.setFromid(fromid);
        rm.setToid(toid);
        rm.setType(MessageType.FAVURL.getType());
        rm.setContent(favURLDTO.getNickname() + Texts.MESSAGE_CONTENT_FAVURL_READ);
        rm.setSendtime(favURLDTO.getReadtime());
        rm.setRefid(favURLDTO.getId());
        return rm;
    }

    @Override
    public Message buildFavurlSendMessage(FavURL favURL) {

        Long fromid = favURL.getFromid();
        Long toid = favURL.getToid();
        User u = userService.Get(fromid);
        Message m = new Message();
        m.setFromid(fromid);
        m.setToid(toid);
        m.setType(MessageType.FAVURL.getType());
        m.setContent(u.getNickName() + Texts.MESSAGE_CONTENT_FAVURL);
        m.setSendtime(favURL.getSendtime());
        m.setRefid(favURL.getId());
        return m;
    }
}
