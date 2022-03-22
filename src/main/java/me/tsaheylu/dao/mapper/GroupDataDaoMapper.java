package me.tsaheylu.dao.mapper;

import me.tsaheylu.model.GroupData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GroupDataDaoMapper {

    GroupData Get(@Param("id") Long id);

    Long Insert(GroupData groupData);

    void Update(GroupData groupData);

    void Delete(@Param("id") Long id);

    GroupData getByToIdAndGroupId(
            @Param("toid") Long toid, @Param("groupid") Long groupid);

    List<GroupData> getByGroupId(@Param("groupid") Long groupid);

    List<GroupData> getByFromIdAndToId(
            @Param("fromid") Long fromid, @Param("toid") Long toid);

    List<GroupData> GetByGroupIdList(
            @Param("list") List<String> groupidlist);

    Integer getNumByGroupId(@Param("groupid") String groupid);

    void batchDelete(@Param("list") List<GroupData> list);

    void batchUpdate(@Param("list") List<GroupData> list);
}
