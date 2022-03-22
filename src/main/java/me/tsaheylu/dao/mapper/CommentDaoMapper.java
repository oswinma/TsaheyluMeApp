package me.tsaheylu.dao.mapper;

import me.tsaheylu.dto.CommentDTO;
import me.tsaheylu.model.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CommentDaoMapper {

    List<CommentDTO> getFrdComsByUrlid(
            @Param("urlid") Long urlid,
            @Param("userid") Long userid,
            @Param("startCursor") Long startCursor);

    List<CommentDTO> getPubComsByUrlid(
            @Param("urlid") Long urlid, @Param("startCursor") Long startCursor);

    List<CommentDTO> getPubComsByFavurlid(
            @Param("favurlid") Long favurlid, @Param("startCursor") Long startCursor);

    List<CommentDTO> getPubComsByUrl(
            @Param("url") String url, @Param("startCursor") Long startCursor);

    List<CommentDTO> getFrdComsByFavurlid(
            @Param("userid") Long userid,
            @Param("favurlid") Long favurlid,
            @Param("startCursor") Long startCursor);

    List<CommentDTO> getFrdComsByUrl(
            @Param("userid") Long userid,
            @Param("url") String url,
            @Param("startCursor") Long startCursor);

    List<CommentDTO> getPubComsByHost(
            @Param("host") String host, @Param("startCursor") Long startCursor);

    Comment Get(@Param("id") Long id);

    void Insert(Comment comment);

    void Update(Comment comment);

    void Delete(@Param("id") Long id);
}
