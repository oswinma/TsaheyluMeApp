package me.tsaheylu.repository;

import me.tsaheylu.dto.MessageDTO;
import me.tsaheylu.model.Message;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepo extends CrudRepository<Message, Long> {


    @Query(value = "select count(*) from message " +
            " where toid = :toid" +
            " and status = 0", nativeQuery = true)
    Integer getUnReadMsgNum(Long toid);


    @Query("        SELECT new me.tsaheylu.dto.MessageDTO(m,a) " +
            "      from Message as m" +
            "      left join User as a on m.fromid = a.id " +
            "      where m.id = :id " +
            "      order by m.sendtime desc")
    MessageDTO getDtoById(Long id);

    @Query("        SELECT new me.tsaheylu.dto.MessageDTO(m,a) " +
            "      from Message as m" +
            "      left join User as a on m.fromid = a.id " +
            "      where m.id in :idlist " +
            "      order by m.sendtime desc")
    List<MessageDTO> getDtoListById(List<Long> idlist);

    @Query("        SELECT new me.tsaheylu.dto.MessageDTO(m,a) " +
            "      from Message as m" +
            "      left join User as a on m.fromid = a.id " +
            "      where m.fromid =:fromid " +
            " and (  m.status =:status )" +
            "      order by m.sendtime desc")
    List<MessageDTO> getMessageDTOListByFromid(Long fromid, Integer status);
}

