package me.tsaheylu.repository;

import me.tsaheylu.common.FavURLStatus;
import me.tsaheylu.dto.FavURLDTO;
import me.tsaheylu.dto.MessageDTO;
import me.tsaheylu.model.FavURL;
import me.tsaheylu.model.Message;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FavurlRepo extends CrudRepository<FavURL, Long> {


    @Query("        SELECT new me.tsaheylu.dto.FavURLDTO(f,urlinfo,u) " +
            "      from FavURL as f" +
            "      left join User as u on f.fromid = u.id " +
            "      left join URLInfo as urlinfo on f.urlid = urlinfo.id " +
            "      where f.id = :id " +
            "      order by f.sendtime desc")
    FavURLDTO getDtoById(Long id);

    @Query("        SELECT new me.tsaheylu.dto.FavURLDTO(f,urlinfo,u) " +
            "      from FavURL as f" +
            "      left join User as u on f.fromid = u.id " +
            "      left join URLInfo as urlinfo on f.urlid = urlinfo.id " +
            "      where f.id in :idlist" +
            "      order by f.sendtime desc")
    List<FavURLDTO> getDtoListById(List<Long> idlist);


    @Query(value = "select count(*) from favurl " +
            "       where toid = :toid  " +
            "       and status = :status ", nativeQuery = true)
    int getNumByToidAndStatus(Long toid, Integer status);


    @Query("        SELECT new me.tsaheylu.dto.FavURLDTO(favurl,urlinfo,user) " +
            "      from FavURL as favurl" +
            "      left join User as user on favurl.fromid = user.id " +
            "      left join URLInfo as urlinfo on favurl.urlid = urlinfo.id " +
            "      where favurl.toid = :toid" +
            " and favurl.status = :status " +
            "      order by favurl.sendtime desc")
    List<FavURLDTO> getDtoListByToidAndStatus(Long toid, Integer status);

}

