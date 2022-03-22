package me.tsaheylu.dao.mapper;

import me.tsaheylu.model.Message;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MessageDaoMapper {

    @MapKey("id")
    List<Message> getMessages(
            @Param("toid") Long toid, @Param("startCursor") Long startCursor);

    Integer getUnReadMsgNum(@Param("toid") Long toid);

    void BatchUpdate(List<Message> list);

    Message getMessageByRefid(@Param("refid") Long refid);

    Message Get(@Param("id") Long id);

    void Insert(Message message);

    void Update(Message message);

    void Delete(@Param("id") Long id);

    void batchInsert(@Param("list") List<Message> list);

    List<Message> getMessageByRefidList(
            @Param("toid") Long toid, @Param("type") String type, @Param("list") List<Long> refidlist);
}
