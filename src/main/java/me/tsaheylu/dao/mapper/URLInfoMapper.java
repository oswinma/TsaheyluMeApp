package me.tsaheylu.dao.mapper;

import me.tsaheylu.model.URLInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface URLInfoMapper {

  public abstract URLInfo getURLInfoByUrl(@Param("url") String url);

  public abstract List<URLInfo> batchgetURLInfoByID(@Param("list") List<Long> list);

  public abstract List<URLInfo> getURLInfoByHost(@Param("host") String host);

  public abstract URLInfo Get(@Param("id") Long id);

  public abstract void Insert(URLInfo urlinfo);

  public abstract void batchInsert(@Param("list") List<URLInfo> list);

  public abstract void Update(URLInfo urlinfo);

  public abstract void Delete(@Param("id") Long id);

  public abstract Long getRecomURLInfoIdForUser(
      @Param("fromid") Long fromid, @Param("toid") Long toid);

  public abstract Long getRecomOldURLInfoIdForUser();

  public abstract int getURLInfoNumByURL(@Param("url") String url);
}
