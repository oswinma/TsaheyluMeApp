package me.tsaheylu.service;


import me.tsaheylu.dto.FavURLDTO;
import me.tsaheylu.dto.MessageNumDTO;
import me.tsaheylu.model.FavURL;
import me.tsaheylu.model.Group;

import java.util.Map;

public interface PushChannelService {

    public abstract Map<String, String> setupChannel(Long id);

    public abstract void removeGroupsFromChannel(Group group);

    public abstract void sendMsgNumToChannel(MessageNumDTO msgnum);

    public abstract void removeMsgNumFromChannel(MessageNumDTO msgnum);

    public abstract void removeFromChannel(FavURL favURL);

    public abstract void sendToChannel(FavURL favURL);

    public abstract void sendGroupsToChannel(int op, Group group);


}
