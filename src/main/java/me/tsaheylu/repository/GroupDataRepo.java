package me.tsaheylu.repository;

import me.tsaheylu.dto.FavURLDTO;
import me.tsaheylu.model.FavURL;
import me.tsaheylu.model.GroupData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface GroupDataRepo extends CrudRepository<GroupData, Long> {


    List<GroupData> findByGroupid(Long groupid);

    GroupData findByToidAndGroupid(Long toid, Long fromGroupId);
}

