package me.tsaheylu.controller;


import lombok.RequiredArgsConstructor;
import me.tsaheylu.DtoMapper.GroupDtoMapper;
import me.tsaheylu.dto.GroupDTO;
import me.tsaheylu.model.Group;
import me.tsaheylu.service.GroupService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller // This means that this class is a Controller
@RequestMapping(path = "/api/group") // This means URL's start with /demo (after Application path)
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;
    private final GroupDtoMapper groupDtoMapper;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    private GroupDTO create(@RequestBody GroupDTO groupDTO) {
        Group group = groupDtoMapper.dtoTo(groupDTO);
        return groupDtoMapper.toDto(groupService.save(group));
    }

    @PutMapping
    private GroupDTO update(@RequestBody GroupDTO groupDTO) {
        Group group = groupDtoMapper.dtoTo(groupDTO);
        return groupDtoMapper.toDto(groupService.update(group));
    }

    @GetMapping(value = "/{id}")
    private GroupDTO get(@PathVariable Long id) {
        return groupDtoMapper.toDto(groupService.get(id));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    private void delete(@PathVariable Long id) {
        groupService.delete(id);
    }


    @GetMapping
    private List<GroupDTO> getByFromId(@RequestParam Long fromid) {
        return groupDtoMapper.toDtoList(groupService.getByFromId(fromid));
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
