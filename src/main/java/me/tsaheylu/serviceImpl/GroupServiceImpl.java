package me.tsaheylu.serviceImpl;

import lombok.RequiredArgsConstructor;
import me.tsaheylu.dao.mapper.GroupDaoMapper;
import me.tsaheylu.model.Group;
import me.tsaheylu.service.GroupService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupDaoMapper groupMapper;

    @Override
    public Group save(Group group) {
        group.setId(groupMapper.Insert(group));
        return group;
    }

    @Override
    public Group update(Group group) {
        groupMapper.Update(group);
        return group;
    }

    @Override
    public Group get(Long id) {
        return groupMapper.Get(id);
    }

    @Override
    public void delete(Long id) {
        groupMapper.Delete(id);
    }

    @Override
    public List<Group> getByFromId(Long fromid) {
        return groupMapper.getByFromId(fromid);
    }


}
