package me.tsaheylu.serviceImpl;

import lombok.RequiredArgsConstructor;
import me.tsaheylu.model.GroupData;
import me.tsaheylu.repository.GroupDataRepo;
import me.tsaheylu.service.GroupDataService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupDataServiceImpl implements GroupDataService {

    private final GroupDataRepo groupDataRepo;

    @Override
    public GroupData get(Long id) {

        Optional<GroupData> groupDataOptional = groupDataRepo.findById(id);
        if (groupDataOptional.isPresent()) {
            return groupDataOptional.get();
        } else {
            return null;
        }
    }

    @Override
    public List<GroupData> getByGroupId(Long groupId) {
        return groupDataRepo.findByGroupid(groupId);
    }

    @Override
    public GroupData save(GroupData groupData) {

        return groupDataRepo.save(groupData);
    }

    @Override
    public GroupData update(GroupData groupData) {
        return groupDataRepo.save(groupData);
    }

    @Override
    public void delete(Long id) {
        groupDataRepo.deleteById(id);
    }


    @Override
    public GroupData moveFriend(Long toid, Long fromGroupId, Long toGroupId) {

        if (toid != null && fromGroupId != null && toGroupId != null) {
            final GroupData gdf = groupDataRepo.findByToidAndGroupid(toid, fromGroupId);

            if (gdf != null) {
//        /*			  ofy().transact(new VoidWork() {
//        public void vrun() {*/
//                groupDataMapper.Delete(gdf.getId());
//                GroupData gdt = new GroupData();
//                gdt.setToid(toid);
//                gdt.setGroupid(toGroupId);
//                gdt.setAddtime(DateUtils.getCurrentTime());
                gdf.setGroupid(toGroupId);
                groupDataRepo.save(gdf);
        /*			        }
        });		*/

                return gdf;
            }
        }
        return null;
    }

}
