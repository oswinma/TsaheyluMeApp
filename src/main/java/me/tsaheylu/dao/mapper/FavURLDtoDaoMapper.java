package me.tsaheylu.dao.mapper;

import me.tsaheylu.dto.FavURLDTO;
import me.tsaheylu.model.FavURL;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface FavURLDtoDaoMapper {

    List<FavURLDTO> getFavURLShowListByToidStatus(
            @Param("toid") Long toid, @Param("status") int status);

    List<FavURLDTO> getFavurlShowListBySendtime(
            @Param("toid") Long toid, @Param("sendtime") Date sendtime);

    List<FavURLDTO> getFavURLShowListByToidStatusLimit(
            @Param("toid") Long toid, @Param("status") int status, @Param("startCursor") Long fromlimit);

    List<FavURLDTO> getFavURLShowListByToidTwoStatusLimit(
            @Param("toid") Long toid,
            @Param("status1") int status1,
            @Param("status2") int status2,
            @Param("startCursor") Long fromlimit);

    List<FavURLDTO> getFavFavURLShowListByToidLimit(
            @Param("toid") Long toid, @Param("startCursor") Long fromlimit);

    List<FavURLDTO> searchFavURLShowByToidWordLimit(
            @Param("toid") Long toid, @Param("word") String word, @Param("startCursor") Long startCursor);

    List<FavURLDTO> getFavURLShowListByFromidLimit(
            @Param("fromid") Long fromid, @Param("startCursor") Long startCursor);

    FavURLDTO getById(
            @Param("id") Long id);

    List<FavURLDTO> getListById(
            @Param("list") List<FavURL> list);
}
