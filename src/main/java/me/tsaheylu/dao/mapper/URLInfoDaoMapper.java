package me.tsaheylu.dao.mapper;

import me.tsaheylu.model.URLInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface URLInfoDaoMapper {

    URLInfo getURLInfoByUrl(@Param("url") String url);

    List<URLInfo> batchgetURLInfoByID(@Param("list") List<Long> list);

    List<URLInfo> getURLInfoByHost(@Param("host") String host);

    URLInfo Get(@Param("id") Long id);

    void Insert(URLInfo urlinfo);

    void batchInsert(@Param("list") List<URLInfo> list);

    void Update(URLInfo urlinfo);

    void Delete(@Param("id") Long id);

    Long getRecomURLInfoIdForUser(
            @Param("fromid") Long fromid, @Param("toid") Long toid);

    Long getRecomOldURLInfoIdForUser();

    int getURLInfoNumByURL(@Param("url") String url);
}
