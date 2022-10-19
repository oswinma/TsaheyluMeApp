package me.tsaheylu.repository;

import me.tsaheylu.dto.ContactDTO;
import me.tsaheylu.dto.InvitationDTO;
import me.tsaheylu.model.Friend;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FriendRepo extends CrudRepository<Friend, Long> {


    List<Friend> findByFromid(Long fromid);

    Friend findByFromidAndToid(Long fromid, Long toid);

    @Query("        SELECT new me.tsaheylu.dto.ContactDTO(a,b) " +
            "      from Friend as b" +
            "      left join User as a on b.toid = a.id " +
            "      where b.fromid = :fromid " +
            "      and (b.status = 1 or b.status =2) " +
            "      and b.toid <> :toid" +
            "      order by b.bondtime desc")
    List<ContactDTO> findContactDTOListByFromid(Long fromid, Long toid);

    Friend findByToidAndStatus(Long toid, int status);


    @Query("        SELECT new me.tsaheylu.dto.InvitationDTO(b,a) " +
            "      from Friend as b" +
            "      left join User as a on b.fromid = a.id " +
            "      where b.fromid = :fromid " +
            "      and (:status is null or b.status = :status) " +
            "      order by b.bondtime desc")
    List<InvitationDTO> getInvitationDTOList(Long fromid, int status);
}

