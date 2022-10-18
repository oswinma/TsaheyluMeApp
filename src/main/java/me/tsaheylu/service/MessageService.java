package me.tsaheylu.service;

import me.tsaheylu.dto.MessageDTO;
import me.tsaheylu.model.Message;
import org.springframework.scheduling.annotation.Async;

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
}
