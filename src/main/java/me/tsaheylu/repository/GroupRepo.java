package me.tsaheylu.repository;

import me.tsaheylu.dto.GroupDTO;
import me.tsaheylu.dto.MessageDTO;
import me.tsaheylu.model.Group;
import me.tsaheylu.model.Message;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GroupRepo extends CrudRepository<Group, Long> {


    @Query("        SELECT new me.tsaheylu.dto.GroupDTO(g) " +
            "      from Group as g" +
//            "      left join User as a on m.fromid = a.id " +
            "      where g.id = :id " +
            "      order by g.createdTime desc")
    GroupDTO getDtoById(Long id);

    @Query("        SELECT new me.tsaheylu.dto.GroupDTO(g) " +
            "      from Group as g" +
//            "      left join User as a on m.fromid = a.id " +
            "      where g.id in :idlist " +
            "      order by g.createdTime desc")
    List<GroupDTO> getDtoListById(List<Long> idlist);

}

