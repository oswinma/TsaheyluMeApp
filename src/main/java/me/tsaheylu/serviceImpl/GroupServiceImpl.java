package me.tsaheylu.serviceImpl;

import lombok.RequiredArgsConstructor;
import me.tsaheylu.model.Group;
import me.tsaheylu.repository.GroupRepo;
import me.tsaheylu.service.GroupService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRepo groupRepo;


    @Override
    public Group save(Group group) {
        return groupRepo.save(group);
    }

    @Override
    public Group update(Group group) {
        return groupRepo.save(group);
    }

    @Override
    public Group get(Long id) {

        Optional<Group> opt = groupRepo.findById(id);
        if (opt.isPresent()) {
            return opt.get();
        }
        return null;
    }

    @Override
    public void delete(Long id) {
        groupRepo.deleteById(id);
    }

    @Override
    public List<Group> getByFromId(Long fromid) {
        return groupRepo.findByfromid(fromid);
    }

}
