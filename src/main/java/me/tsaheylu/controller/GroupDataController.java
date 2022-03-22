package me.tsaheylu.controller;


import lombok.RequiredArgsConstructor;
import me.tsaheylu.DtoMapper.GroupDataDtoMapper;
import me.tsaheylu.dto.GroupDataDTO;
import me.tsaheylu.model.GroupData;
import me.tsaheylu.service.GroupDataService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller // This means that this class is a Controller
@RequestMapping(path = "/api/groupdata") // This means URL's start with /demo (after Application path)
@RequiredArgsConstructor
public class GroupDataController {

    private final GroupDataService groupDataService;
    private final GroupDataDtoMapper groupDataDtoMapper;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    private GroupDataDTO create(@RequestBody GroupDataDTO groupDataDTO) {
        GroupData groupData = groupDataDtoMapper.dtoTo(groupDataDTO);
        return groupDataDtoMapper.toDto(groupDataService.save(groupData));
    }

    @PutMapping
    private GroupDataDTO update(@RequestBody GroupDataDTO groupDataDTO) {
        GroupData groupData = groupDataDtoMapper.dtoTo(groupDataDTO);
        return groupDataDtoMapper.toDto(groupDataService.update(groupData));
    }

    @GetMapping(value = "/{id}")
    private GroupDataDTO get(@PathVariable Long id) {
        return groupDataDtoMapper.toDto(groupDataService.get(id));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void delete(@PathVariable Long id) {
        groupDataService.delete(id);
    }

    @GetMapping
    private List<GroupDataDTO> getByGroupId(@RequestParam Long groupId) {
        return groupDataDtoMapper.toDtoList(groupDataService.getByGroupId(groupId));
    }

    @PutMapping(path = "/move")
    private GroupDataDTO moveFriend(@RequestParam Long toid, @RequestParam Long fromGroupId, @RequestParam Long toGroupId) {

        return groupDataDtoMapper.toDto(groupDataService.moveFriend(toid, fromGroupId, toGroupId));
    }

//    /api/group
///api/group/add
///api/group/create
///api/group/data
///api/group/delete
///api/group/friendsnum
///api/group/info
///api/group/move
///api/group/remove
///api/group/update

}
