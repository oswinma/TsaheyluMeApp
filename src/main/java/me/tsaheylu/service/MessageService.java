package me.tsaheylu.service;

import me.tsaheylu.dto.FavURLDTO;
import me.tsaheylu.dto.InvitationDTO;
import me.tsaheylu.dto.MessageDTO;
import me.tsaheylu.model.FavURL;
import me.tsaheylu.model.Message;
import org.springframework.scheduling.annotation.Async;

import java.util.List;

public interface MessageService {
    public abstract Integer getUnreadNum();

//  public abstract List<MessageInfo> ConMessagetoMessageInfo(List<Message> list);
//
//  public abstract Message createFavurlReadMessage(FavURLShow fu);
//
//  public abstract Message createFavurlSendMessage(FavURL ful);
//
//  public abstract void updateMsgNumToChannel(Long toid);

    @Async
    public abstract MessageDTO createMessage(Message message);

    public abstract MessageDTO save(MessageDTO messageDTO);

    Message saveEntity(Message message);

    public abstract void delete(Long id);

    public abstract MessageDTO get(Long id);

    public abstract MessageDTO update(MessageDTO messageDTO);

    List<MessageDTO> getMessageDTOListByFromid(Long fromid);

    List<MessageDTO> getUnreadMessageDTOListByFromid(Long fromid);

    Message buildFavurlReadMessage(FavURLDTO favURLDTO);

    Message buildFavurlSendMessage(FavURL fu);



    void saveAll(List<Message> mlist);

    void updateMsgNumToChannel(Message message);
}
