package me.tsaheylu.dao.mapper;

import me.tsaheylu.dto.CommentDTO;
import me.tsaheylu.model.Comment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentMapper {

  public abstract List<CommentDTO> getFrdComsByUrlid(
      @Param("urlid") Long urlid,
      @Param("userid") Long userid,
      @Param("startCursor") Long startCursor);

  public abstract List<CommentDTO> getPubComsByUrlid(
      @Param("urlid") Long urlid, @Param("startCursor") Long startCursor);

  public abstract List<CommentDTO> getPubComsByFavurlid(
      @Param("favurlid") Long favurlid, @Param("startCursor") Long startCursor);

  public abstract List<CommentDTO> getPubComsByUrl(
      @Param("url") String url, @Param("startCursor") Long startCursor);

  public abstract List<CommentDTO> getFrdComsByFavurlid(
      @Param("userid") Long userid,
      @Param("favurlid") Long favurlid,
      @Param("startCursor") Long startCursor);

  public abstract List<CommentDTO> getFrdComsByUrl(
      @Param("userid") Long userid,
      @Param("url") String url,
      @Param("startCursor") Long startCursor);

  public abstract List<CommentDTO> getPubComsByHost(
      @Param("host") String host, @Param("startCursor") Long startCursor);

  public abstract Comment Get(@Param("id") Long id);

  public abstract void Insert(Comment comment);

  public abstract void Update(Comment comment);

  public abstract void Delete(@Param("id") Long id);
}
