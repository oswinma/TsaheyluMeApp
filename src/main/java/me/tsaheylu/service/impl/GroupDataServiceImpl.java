package me.tsaheylu.service.impl;

import lombok.RequiredArgsConstructor;
import me.tsaheylu.dao.mapper.GroupDataDaoMapper;
import me.tsaheylu.model.GroupData;
import me.tsaheylu.service.GroupDataService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupDataServiceImpl implements GroupDataService {

    private final GroupDataDaoMapper groupDataMapper;

    @Override
    public GroupData get(Long id) {
        return groupDataMapper.Get(id);
    }

    @Override
    public List<GroupData> getByGroupId(Long groupId) {
        return groupDataMapper.getByGroupId(groupId);
    }

    @Override
    public GroupData save(GroupData groupData) {
        return null;
    }

    @Override
    public GroupData update(GroupData groupData) {
        return null;
    }

    @Override
    public void delete(Long id) {
        groupDataMapper.Delete(id);
    }


    @Override
    public GroupData moveFriend(Long toid, Long fromGroupId, Long toGroupId) {

        if (toid != null && fromGroupId != null && toGroupId != null) {
            final GroupData gdf = groupDataMapper.getByToIdAndGroupId(toid, fromGroupId);

            if (gdf != null) {
//        /*			  ofy().transact(new VoidWork() {
//        public void vrun() {*/
//                groupDataMapper.Delete(gdf.getId());
//                GroupData gdt = new GroupData();
//                gdt.setToid(toid);
//                gdt.setGroupid(toGroupId);
//                gdt.setAddtime(DateUtils.getCurrentTime());
                gdf.setGroupid(toGroupId);
                groupDataMapper.Update(gdf);
        /*			        }
        });		*/

                return gdf;
            }
        }
        return null;
    }

}
