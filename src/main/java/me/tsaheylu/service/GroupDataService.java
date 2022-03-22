package me.tsaheylu.service;

import me.tsaheylu.model.GroupData;

import java.util.List;

public interface GroupDataService {
    GroupData save(GroupData groupData);

    GroupData update(GroupData groupData);

    void delete(Long id);

    GroupData get(Long id);

    List<GroupData> getByGroupId(Long groupId);

    GroupData moveFriend(Long toid, Long fromGroupId, Long toGroupId);
}
