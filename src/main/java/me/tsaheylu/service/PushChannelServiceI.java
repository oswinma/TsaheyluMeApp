package me.tsaheylu.service;


import me.tsaheylu.dto.FavURLDTO;
import me.tsaheylu.model.FavURL;
import me.tsaheylu.model.Group;

public interface PushChannelServiceI {

    public abstract String setupChannel(Long id);

    public abstract void removeGroupsFromChannel(Group group);

//    public abstract void sendMsgNumToChannel(MsgNum msgnum);
//
//    public abstract void removeMsgNumFromChannel(MsgNum msgnum);

    public abstract void removeFromChannel(FavURL favurl);

    public abstract void sendToChannel(FavURLDTO favURLDTO);

    public abstract void sendGroupsToChannel(int op, Group group);
}
