package me.tsaheylu.service.impl;


import me.tsaheylu.common.Constants;
import me.tsaheylu.common.FavURLStatus;
import me.tsaheylu.dao.mapper.FavURLMapper;
import me.tsaheylu.dao.mapper.UserMapper;
import me.tsaheylu.dto.FavURLShow;
import me.tsaheylu.model.FavURL;
import me.tsaheylu.service.FavURLService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class FavURLServiceImpl implements FavURLService {

  @Autowired private FavURLMapper favurlMapper;
  @Autowired private UserMapper userMapper;

  @Override
  public HashMap<String, Object> getFavurlsByStatus(Long toids, String startCursor, int status) {
    // TODO Auto-generated method stub
    if (toids != null) {

      Long toid = Long.valueOf(toids);

      Long fromlimit = 0l;
      if (startCursor != null && !startCursor.equals("")) {
        fromlimit = Long.valueOf(startCursor);
      }

      List<FavURLShow> list =
          favurlMapper.getFavURLShowListByToidStatusLimit(toid, status, fromlimit);

      if (!list.isEmpty()) {
        fromlimit = fromlimit + Constants.PAGE_SIZE;
      }

      Map<String, Object> data = new HashMap<String, Object>();
      data.put("FavURLShows", list);
      data.put("startCursor", fromlimit);
      return (HashMap<String, Object>) data;
    }
    return null;
  }

  @Override
  public FavURL getArchive(Long toid) {

    return favurlMapper.Get(toid);
  }

  @Override
  public FavURL getNew(Long toid) {
    return favurlMapper.Get(toid);
  }

  @Override
  public HashMap<String, Object> getFav(Long userid, String startCursor) {

    if (userid != null) {

      Long toid = Long.valueOf(userid);
      Long fromlimit = 0l;
      if (startCursor != null && !startCursor.equals("")) {
        fromlimit = Long.valueOf(startCursor);
      }

      List<FavURLShow> list = favurlMapper.getFavFavURLShowListByToidLimit(toid, fromlimit);

      if (!list.isEmpty()) {
        fromlimit = fromlimit + Constants.PAGE_SIZE;
      }

      Map<String, Object> data = new HashMap<String, Object>();
      data.put("FavURLShows", list);
      data.put("startCursor", fromlimit);
      return (HashMap<String, Object>) data;
    }

    return null;
  }

  @Override
  public boolean batchUpdateFavurlStatus(List<FavURL> favURLS) {

    ArrayList<FavURL> list = new ArrayList<FavURL>();
    Long toid = 0l;
    int increase = 0;
    for (FavURL favURL : favURLS) {
      Long id = favURL.getId();
      int status = favURL.getStatus();

      FavURL fu = favurlMapper.Get(Long.valueOf(id));

      if (fu != null) {
        if (status == FavURLStatus.NEW) {
          increase = increase + 1;
        }

        if (status == FavURLStatus.ARCHIVE) {
          increase = increase - 1;
        }

        if (status == FavURLStatus.REMOVE) {
          fu.setFav(false);
          if (fu.getStatus() == FavURLStatus.NEW) {
            increase = increase - 1;
          }
        }

        toid = fu.getToid();
        fu.setStatus(status);
        list.add(fu);
      }

      if (toid != 0l) {
        favurlMapper.BatchUpdate(list);
        return true;
      }
    }
    return false;
  }
}
