package me.tsaheylu.service;

import me.tsaheylu.dto.FavURLDTO;
import me.tsaheylu.model.FavURL;

import java.util.HashMap;
import java.util.List;

public interface FavURLService {

//  Map<String, Object> getFavFavurls(Long userid, String startCursor);
//
//  Map<String, Object> getMyFavurls(String userids, String startCursor);
//
//  Map<String, Object> getNewFavurls(Long userid, String startCursor);
//
//  Map<String, Object> getNewComingFavurls(Long userid, String favurlid);
//
//  Map<String, Object> getPendingFavurls(Long userids);

    HashMap<String, Object> getFavurlsByStatus(Long toids, String startCursor, int status);

    FavURL getArchive(Long valueOf);

    FavURL getNew(Long toid);

    HashMap<String, Object> getFav(Long id, String startCursor);

    boolean batchUpdateFavurlStatus(List<FavURL> json);

    FavURL save(FavURL favURL);

    FavURLDTO update(FavURLDTO favURLDTO);


    FavURLDTO getDto(Long id);

    void delete(Long id);

    FavURLDTO createFavurl(FavURLDTO favURLDTO);

    List<FavURL> sendFavurls(Long fromid, String groupidss, boolean toall, boolean tome, String url, String urltitles, String iconurls);

    FavURLDTO updateChannel(Long id, String channel);
}
