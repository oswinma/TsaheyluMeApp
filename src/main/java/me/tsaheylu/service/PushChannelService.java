package me.tsaheylu.service;


import me.tsaheylu.dto.FavURLDTO;
import me.tsaheylu.dto.MessageNumDTO;
import me.tsaheylu.model.FavURL;
import me.tsaheylu.model.Group;

public interface PushChannelService {

    public abstract String setupChannel(Long id);

    public abstract void removeGroupsFromChannel(Group group);

    public abstract void sendMsgNumToChannel(MessageNumDTO msgnum);

    public abstract void removeMsgNumFromChannel(MessageNumDTO msgnum);

    public abstract void removeFromChannel(FavURL favurl);

    public abstract void sendToChannel(FavURLDTO favurlDto);

    public abstract void sendGroupsToChannel(int op, Group group);


}