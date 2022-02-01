package me.tsaheylu.dao.mapper;

import me.tsaheylu.model.Group;
import me.tsaheylu.model.GroupData;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface GroupMapper {

  public abstract GroupData getGroupdata(
      @Param("toid") String toid, @Param("groupid") String groupid);

  public abstract List<GroupData> getGroupdataByGroupid(@Param("groupid") String groupid);

  public abstract List<GroupData> getGroupdataByGroup(
      @Param("fromid") Long fromid, @Param("toid") Long toid);

  public abstract List<Group> getGroupsByType(
      @Param("fromid") Long fromid, @Param("type") String type);

  public abstract List<Group> getGroups(@Param("fromid") Long fromid);

  public abstract void insertGroupData(GroupData gd);

  public abstract void reomveGroupdata(GroupData gd);

  public abstract List<GroupData> batchgetGroupdataByGroup(
      @Param("list") List<String> groupidlist);

  public abstract Map<Long, Group> batchgetGroupByID(@Param("list") List<Long> list);

  public abstract void batchUpdateGroupData(@Param("list") List<GroupData> list);

  public abstract void batchDeleteGroupData(@Param("list") List<GroupData> list);

  public abstract Integer getFriendNumByGroup(@Param("groupid") String groupid);

  public abstract GroupData getGroupDataByGroupdataid(@Param("groupdataid") Long groupdataid);

  public abstract Group Get(@Param("id") Long id);

  public abstract void Insert(Group group);

  public abstract void Update(Group group);

  public abstract void Delete(@Param("id") Long id);
}
