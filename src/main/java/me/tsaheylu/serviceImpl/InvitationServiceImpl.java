package me.tsaheylu.serviceImpl;

import lombok.RequiredArgsConstructor;
import me.tsaheylu.common.Constants;
import me.tsaheylu.common.FriendStatus;
import me.tsaheylu.common.MessageType;
import me.tsaheylu.common.Texts;
import me.tsaheylu.dao.mapper.FriendDaoMapper;
import me.tsaheylu.dao.mapper.MessageDaoMapper;
import me.tsaheylu.dao.mapper.UserDaoMapper;
import me.tsaheylu.dto.ContactDTO;
import me.tsaheylu.dto.InvitationDTO;
import me.tsaheylu.model.Friend;
import me.tsaheylu.model.Message;
import me.tsaheylu.model.User;
import me.tsaheylu.repository.FriendRepo;
import me.tsaheylu.repository.UserRepo;
import me.tsaheylu.service.FriendService;
import me.tsaheylu.service.InvitationService;
import me.tsaheylu.service.MessageService;
import me.tsaheylu.util.DateUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InvitationServiceImpl implements InvitationService {

    private final FriendRepo friendRepo;

    private final UserRepo userRepo;

    private final MessageService messageService;


    @Override
    public InvitationDTO save(InvitationDTO invitationDTO) {

        Long fromid = invitationDTO.getFromid();
        Long toid = invitationDTO.getToid();

        Optional<User> fromUserOpt = userRepo.findById(fromid);
        Optional<User> toUserOpt = userRepo.findById(toid);

        if (fromUserOpt.isPresent() && toUserOpt.isPresent()) {
            User fromUser = fromUserOpt.get();
            User toUser = toUserOpt.get();

            Friend f = friendRepo.findByFromidAndToid(fromid, toid);

            if (f != null) {
                if (f.getStatus() != FriendStatus.INVALID.getId()) {
                    return null;
                }

            } else {
                f = new Friend();
                f.setFromid(fromid);
                f.setToid(toid);
                f.setBondtime(DateUtils.getCurrentTime());
                f = friendRepo.save(f);
            }

            Message m = new Message(fromid, toid, MessageType.INVITATION.name(), fromUser.getNickname() + Texts.MESSAGE_CONTENT_INVITEFRIEND, f.getId());
            messageService.createMessage(m);

            invitationDTO.setId(f.getId());
            return invitationDTO;
        }

        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public InvitationDTO get(Long id) {
        return null;
    }

    @Override
    public InvitationDTO update(InvitationDTO invitationDTO) {
        return null;
    }

    @Override
    public List<InvitationDTO> getInvitationDTOList(Long fromid) {
        return friendRepo.getInvitationDTOList(fromid, FriendStatus.INVALID.getId());
    }
}
