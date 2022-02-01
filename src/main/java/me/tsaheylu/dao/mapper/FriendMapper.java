package me.tsaheylu.dao.mapper;

import me.tsaheylu.dto.Contact;
import me.tsaheylu.model.Friend;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FriendMapper {

  public abstract List<Friend> getFriends(@Param("fromid") Long fromid, @Param("toid") Long toid);

  public abstract List<Contact> getFriendsContactList(
      @Param("fromid") Long fromid, @Param("toid") Long toid);

  public abstract Friend getFriend(@Param("fromid") Long fromid, @Param("toid") Long toid);

  public abstract Integer isFriend(@Param("fromid") Long fromid, @Param("toid") Long toid);

  public abstract List<Friend> getInvitations(@Param("toid") Long toid);

  public abstract Integer hasAvaliableFriends(
      @Param("fromid") Long fromid, @Param("toid") Long toid);

  public abstract Friend Get(@Param("id") Long id);

  public abstract void Insert(Friend friend);

  public abstract void Update(Friend friend);

  public abstract void Delete(@Param("id") Long id);

  public abstract void batchInsert(@Param("list") List<Friend> list);

  public abstract List<Friend> getSendFriends(
      @Param("fromid") Long fromid, @Param("toid") Long toid);

  public abstract List<Friend> getSendGroupFriends(
      @Param("fromid") Long fromid, @Param("list") List<String> groupidlist);

  public abstract List<Friend> getFavurlSubscriptionUsers(@Param("fromid") Long fromid);

  public abstract List<Friend> getEmailSubscriptionUsers(@Param("fromid") Long fromid);

}
