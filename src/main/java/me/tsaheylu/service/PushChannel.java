package me.tsaheylu.service;

import me.tsaheylu.dto.FavURLDTO;
import me.tsaheylu.dto.MsgNumDTO;
import me.tsaheylu.model.FavURL;
import me.tsaheylu.model.Group;

public interface PushChannel {

  public abstract String setupChannel(Long id);

  public abstract void removeGroupsFromChannel(Group group);

  public abstract void sendMsgNumToChannel(MsgNumDTO msgnum);

  public abstract void removeMsgNumFromChannel(MsgNumDTO msgnum);

  public abstract void removeFromChannel(FavURL favurl);

  public abstract void sendToChannel(FavURLDTO favurlshow);

  public abstract void sendGroupsToChannel(int op, Group group);
}
