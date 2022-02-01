package me.tsaheylu.dao.mapper;

import me.tsaheylu.model.Message;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MessageMapper {

  @MapKey("id")
  public abstract List<Message> getMessages(
      @Param("toid") Long toid, @Param("startCursor") Long startCursor);

  public abstract Integer getUnReadMsgNum(@Param("toid") Long toid);

  public abstract void BatchUpdate(List<Message> list);

  public abstract Message getMessageByRefid(@Param("refid") Long refid);

  public abstract Message Get(@Param("id") Long id);

  public abstract void Insert(Message message);

  public abstract void Update(Message message);

  public abstract void Delete(@Param("id") Long id);

  public abstract void batchInsert(@Param("list") List<Message> list);

  public abstract List<Message> getMessageByRefidList(
      @Param("toid") Long toid, @Param("type") String type, @Param("list") List<Long> refidlist);
}
