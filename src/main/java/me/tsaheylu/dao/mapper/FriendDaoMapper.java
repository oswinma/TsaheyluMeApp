package me.tsaheylu.dao.mapper;

import me.tsaheylu.dto.ContactDTO;
import me.tsaheylu.model.Friend;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FriendDaoMapper {

    List<Friend> getFriends(@Param("fromid") Long fromid, @Param("toid") Long toid);

    List<ContactDTO> getFriendsContactList(
            @Param("fromid") Long fromid, @Param("toid") Long toid);

    Friend getFriend(@Param("fromid") Long fromid, @Param("toid") Long toid);

    Integer isFriend(@Param("fromid") Long fromid, @Param("toid") Long toid);

    List<Friend> getInvitations(@Param("toid") Long toid);

    Integer hasAvaliableFriends(
            @Param("fromid") Long fromid, @Param("toid") Long toid);

    Friend Get(@Param("id") Long id);

    Long Insert(Friend friend);

    void Update(Friend friend);

    void Delete(@Param("id") Long id);

    void batchInsert(@Param("list") List<Friend> list);

    List<Friend> getSendFriends(
            @Param("fromid") Long fromid, @Param("toid") Long toid);

    List<Friend> getSendGroupFriends(
            @Param("fromid") Long fromid, @Param("list") List<String> groupidlist);

    List<Friend> getFavurlSubscriptionUsers(@Param("fromid") Long fromid);

    List<Friend> getEmailSubscriptionUsers(@Param("fromid") Long fromid);

}
