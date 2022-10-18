package me.tsaheylu.service;


import me.tsaheylu.dto.InvitationDTO;

import java.util.List;

public interface InvitationService {


    InvitationDTO save(InvitationDTO invitationDTO);

    void delete(Long id);

    InvitationDTO get(Long id);

    InvitationDTO update(InvitationDTO invitationDTO);

    List<InvitationDTO> getInvitationDTOList(Long fromid);


}
