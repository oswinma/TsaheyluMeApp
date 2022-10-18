package me.tsaheylu.repository;

import me.tsaheylu.dto.ContactDTO;
import me.tsaheylu.model.Friend;
import me.tsaheylu.model.Message;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MessageRepo extends CrudRepository<Message, Long> {


    @Query(value = "select count(*) from message " +
            "where toid = :toid" +
            "and status = 0", nativeQuery = true)
    Integer getUnReadMsgNum(Long toid);
}

