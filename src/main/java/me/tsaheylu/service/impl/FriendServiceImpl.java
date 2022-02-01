package me.tsaheylu.service.impl;

import me.tsaheylu.common.Constants;
import me.tsaheylu.dao.mapper.FriendMapper;
import me.tsaheylu.dao.mapper.UserMapper;
import me.tsaheylu.model.Friend;
import me.tsaheylu.model.User;
import me.tsaheylu.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class FriendServiceImpl implements FriendService {

  @Autowired private FriendMapper friendMapper;
  @Autowired private UserMapper userMapper;

  @Override
  public boolean hasAvaliableFriends(Long userid) {
    if (friendMapper.hasAvaliableFriends(userid, Constants.TsahayluTeamID) > 0) return true;
    else return false;
  }

  @Override
  public Map<String, Object> getTsahayluTeamInfo(Long userid) {
    Friend f = friendMapper.getFriend(userid, Constants.TsahayluTeamID);
    User u = userMapper.Get(Constants.TsahayluTeamID);
    Map<String, Object> data = new HashMap<String, Object>();
    data.put("id", Constants.TsahayluTeamID);
    data.put("avatarURL", u.getAvatarURL());
    data.put("nickname", u.getNickname());
    data.put("status", f.getStatus());
    data.put("popup", f.isPopup());
    data.put("friendid", f.getId());
    return (HashMap<String, Object>) data;
  }
}
