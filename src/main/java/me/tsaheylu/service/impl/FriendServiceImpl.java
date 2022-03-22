package me.tsaheylu.service.impl;

import lombok.RequiredArgsConstructor;
import me.tsaheylu.common.Constants;
import me.tsaheylu.common.FriendStatus;
import me.tsaheylu.common.MessageType;
import me.tsaheylu.common.Texts;
import me.tsaheylu.dao.mapper.FriendDaoMapper;
import me.tsaheylu.dao.mapper.MessageDaoMapper;
import me.tsaheylu.dao.mapper.UserDaoMapper;
import me.tsaheylu.model.Friend;
import me.tsaheylu.model.Message;
import me.tsaheylu.model.User;
import me.tsaheylu.service.FriendService;
import me.tsaheylu.util.DateUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FriendServiceImpl implements FriendService {

    private final FriendDaoMapper friendMapper;
    private final UserDaoMapper userDaoMapper;
    private final MessageDaoMapper messageMapper;

    @Override
    public boolean hasAvaliableFriends(Long userid) {
        if (friendMapper.hasAvaliableFriends(userid, Constants.TsahayluTeamID) > 0) return true;
        else return false;
    }

    @Override
    public Map<String, Object> getTsahayluTeamInfo(Long userid) {
        Friend f = friendMapper.getFriend(userid, Constants.TsahayluTeamID);
        User u = userDaoMapper.Get(Constants.TsahayluTeamID);
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("id", Constants.TsahayluTeamID);
        data.put("avatarURL", u.getAvatarURL());
        data.put("nickname", u.getNickname());
        data.put("status", f.getStatus());
        data.put("popup", f.isPopup());
        data.put("friendid", f.getId());
        return (HashMap<String, Object>) data;
    }

    @Override
    public Friend save(Friend friend) {

        friend.setId(friendMapper.Insert(friend));
        return friend;

    }

    @Override
    public void delete(Long id) {
        friendMapper.Delete(id);
    }

    @Override
    public Friend get(Long id) {
        return friendMapper.Get(id);
    }

    @Override
    public Friend update(Friend friend) {
        friendMapper.Update(friend);
        return friend;
    }

    @Override
    public Friend invite(Friend friend) {

        Long fromid = friend.getFromid();
        Long toid = friend.getToid();

        final User fu = userDaoMapper.Get(fromid);
        User tu = userDaoMapper.Get(toid);

        Friend f = null;
        if (fu != null && tu != null) {

            f = friendMapper.getFriend(fromid, toid);
            if (f != null) {
                if (f.getStatus() != FriendStatus.INVALID.getId()) {
                    return f;
                }

            } else {
                f = new Friend();
                f.setFromid(fromid);
                f.setToid(toid);
                f.setBondtime(DateUtils.getCurrentTime());
                friendMapper.Insert(f);
            }

            Message m =
                    new Message(
                            fromid,
                            toid,
                            MessageType.INVITATION.getType(),
                            fu.getNickname() + Texts.MESSAGE_CONTENT_INVITEFRIEND,
                            f.getId());
            messageMapper.Insert(m);

//            messageservice.updateMsgNumToChannel(m.getToid());

        }

        return f;
    }
}
