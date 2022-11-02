package me.tsaheylu.serviceImpl;

import lombok.RequiredArgsConstructor;
import me.tsaheylu.common.Constants;
import me.tsaheylu.common.FriendStatus;
import me.tsaheylu.common.MessageType;
import me.tsaheylu.common.Texts;
import me.tsaheylu.dto.ContactDTO;
import me.tsaheylu.model.Friend;
import me.tsaheylu.model.Message;
import me.tsaheylu.model.User;
import me.tsaheylu.repository.FriendRepo;
import me.tsaheylu.service.FriendService;
import me.tsaheylu.service.MessageService;
import me.tsaheylu.service.UserService;
import me.tsaheylu.util.DateUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FriendServiceImpl implements FriendService {
    private final FriendRepo friendRepo;
    private final UserService userService;

    private final MessageService messageService;


    @Override
    public boolean hasAvaliableFriends(Long userid) {
        if (friendRepo.getFriendNumber(userid, Constants.TsahayluTeamID) > 0) return true;
        else return false;
    }

    @Override
    public Map<String, Object> getTsahayluTeamInfo(Long userid) {
        Friend f = friendRepo.findByFromidAndToid(userid, Constants.TsahayluTeamID);
        User u = userService.Get(Constants.TsahayluTeamID);
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("id", Constants.TsahayluTeamID);
        data.put("avatarURL", u.getAvatarURL());
        data.put("nickname", u.getNickName());
        data.put("status", f.getStatus());
        data.put("popup", f.isPopup());
        data.put("friendid", f.getId());
        return (HashMap<String, Object>) data;
    }

    @Override
    public Friend save(Friend friend) {
        friend = friendRepo.save(friend);
        return friend;
    }

    @Override
    public void delete(Long id) {
        friendRepo.deleteById(id);
    }

    @Override
    public Friend get(Long id) {
        Optional<Friend> friendOpt = friendRepo.findById(id);
        if (friendOpt.isPresent()) {
            return friendOpt.get();
        } else {
            return null;
        }
    }

    @Override
    public Friend update(Friend friend) {
        friend = friendRepo.save(friend);
        return friend;
    }

    @Override
    public Friend invite(Friend friend) {

        Long fromid = friend.getFromid();
        Long toid = friend.getToid();

        final User fu = userService.Get(fromid);
        User tu = userService.Get(toid);

        Friend f = null;
        if (fu != null && tu != null) {

            f = friendRepo.findByFromidAndToid(fromid, toid);
            if (f != null) {
                if (f.getStatus() != FriendStatus.INVALID.getId()) {
                    return f;
                }

            } else {
                f = new Friend();
                f.setFromid(fromid);
                f.setToid(toid);
                f.setBondtime(DateUtils.getCurrentTime());
                friendRepo.save(f);
            }

            Message m = new Message(fromid, toid, MessageType.INVITATION.getType(), fu.getNickName() + Texts.MESSAGE_CONTENT_INVITEFRIEND, f.getId());
            messageService.saveEntity(m);

//            messageservice.updateMsgNumToChannel(m.getToid());

        }

        return f;
    }

    @Override
    public List<ContactDTO> getContactDTOList(Long fromid) {

        return friendRepo.findContactDTOListByFromid(fromid, Constants.TsahayluTeamID);
    }

    @Override
    public List<Friend> getSendFriendList(Long fromid) {
        return friendRepo.findSendFriendListByFromid(fromid, Constants.TsahayluTeamID);
    }

    @Override
    public List<Friend> getSendFriendListByFromidAndGroupidList(Long fromid, List<String> groupidlist) {
        return friendRepo.findSendFriendListByFromidAndGroupidList(fromid, groupidlist);
    }


}
