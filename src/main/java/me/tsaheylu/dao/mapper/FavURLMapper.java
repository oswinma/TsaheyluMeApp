package me.tsaheylu.dao.mapper;

import me.tsaheylu.dto.Contact;
import me.tsaheylu.dto.FavURLShow;
import me.tsaheylu.model.FavURL;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface FavURLMapper {

  public abstract List<FavURLShow> getFavURLShowListByToidStatus(
      @Param("toid") Long toid, @Param("status") int status);

  public abstract List<FavURLShow> getFavurlShowListBySendtime(
      @Param("toid") Long toid, @Param("sendtime") Date sendtime);

  public abstract List<FavURLShow> getFavURLShowListByToidStatusLimit(
      @Param("toid") Long toid, @Param("status") int status, @Param("startCursor") Long fromlimit);

  public abstract List<FavURLShow> getFavURLShowListByToidTwoStatusLimit(
      @Param("toid") Long toid,
      @Param("status1") int status1,
      @Param("status2") int status2,
      @Param("startCursor") Long fromlimit);

  public abstract List<FavURLShow> getFavFavURLShowListByToidLimit(
      @Param("toid") Long toid, @Param("startCursor") Long fromlimit);

  public abstract List<FavURLShow> searchFavURLShowByToidWordLimit(
      @Param("toid") Long toid, @Param("word") String word, @Param("startCursor") Long startCursor);

  public abstract List<FavURLShow> getFavURLShowListByFromidLimit(
      @Param("fromid") Long fromid, @Param("startCursor") Long startCursor);

  public abstract Integer getNumByToidStatus(@Param("toid") Long toid, @Param("status") int status);

  public abstract void BatchInsert(@Param("list") List<FavURL> list);

  public abstract void BatchUpdate(@Param("list") List<FavURL> list);

  public abstract List<FavURL> getFavURLListBySerial(@Param("serial") Long serial);

  public abstract FavURL Get(@Param("id") Long id);

  public abstract void Insert(FavURL favurl);

  public abstract void Update(FavURL favurl);

  public abstract void Delete(@Param("id") Long id);

  public abstract List<Contact> getShareUserListByUrlid(@Param("urlid") Long urlid);

  public abstract List<Contact> getFavUserListByUrlid(@Param("urlid") Long urlid);
}
