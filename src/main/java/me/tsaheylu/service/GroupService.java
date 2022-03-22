package me.tsaheylu.service;

import me.tsaheylu.model.Group;

import java.util.List;

public interface GroupService {
    Group save(Group group);

    Group update(Group group);

    Group get(Long id);

    void delete(Long id);

    List<Group> getByFromId(Long fromid);


}
