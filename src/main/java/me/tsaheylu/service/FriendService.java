package me.tsaheylu.service;

//import com.github.scribejava.core.model.OAuth1AccessToken;
//import com.github.scribejava.core.model.OAuth2AccessToken;
//import com.github.scribejava.core.oauth.OAuth10aService;
//import com.github.scribejava.core.oauth.OAuth20Service;

import me.tsaheylu.model.Friend;

import java.util.Map;

public interface FriendService {
    boolean hasAvaliableFriends(Long id);

    Map<String, Object> getTsahayluTeamInfo(Long id);

    Friend save(Friend friend);

    void delete(Long id);

    Friend get(Long id);

    Friend update(Friend friend);

    Friend invite(Friend friend);

//  public abstract HashMap<String, Object> getGoogleFriendsList(
//      Long fromid, OAuth20Service gservice, OAuth2AccessToken accessToken);
//
//  public abstract HashMap<String, Object> getFacebookFriends(
//      UserOpenID uo, OAuth20Service fservice, OAuth2AccessToken accessToken);
//
//  public abstract HashMap<String, Object> getTwitterFriends(
//      UserOpenID uo, OAuth10aService tservice, OAuth1AccessToken accessToken);

//  public abstract boolean inviteFriend(Long fromid, Long toid);
//
//  public abstract List<Invitation> ConFriendtoInvitation(List<Friend> list);
//
//  public abstract List<Contact> ConFriendtoContacts(List<Friend> list, Long userid);
}
