package me.tsaheylu.dao.mapper;

import me.tsaheylu.model.Group;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GroupDaoMapper {

/*  public abstract GroupData getGroupdata(
      @Param("toid") String toid, @Param("groupid") String groupid);

  public abstract List<GroupData> getGroupdataByGroupid(@Param("groupid") String groupid);

  public abstract List<GroupData> getGroupdataByGroup(
      @Param("fromid") Long fromid, @Param("toid") Long toid);*/

    List<Group> getByFromIdAndType(
            @Param("fromid") Long fromid, @Param("type") String type);

    List<Group> getByFromId(@Param("fromid") Long fromid);

/*  public abstract void insertGroupData(GroupData gd);

  public abstract void reomveGroupdata(GroupData gd);

  public abstract List<GroupData> batchgetGroupdataByGroup(
      @Param("list") List<String> groupidlist);*/

    List<Group> getByIdList(@Param("list") List<Long> idList);

/*  public abstract void batchUpdateGroupData(@Param("list") List<GroupData> list);

  public abstract void batchDeleteGroupData(@Param("list") List<GroupData> list);*/

//  public abstract Integer getFriendNumByGroup(@Param("groupid") String groupid);

//  public abstract GroupData getGroupDataByGroupdataid(@Param("groupdataid") Long groupdataid);

    Group Get(@Param("id") Long id);

    Long Insert(Group group);

    void Update(Group group);

    void Delete(@Param("id") Long id);
}
