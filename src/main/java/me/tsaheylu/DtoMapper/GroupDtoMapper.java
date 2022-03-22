package me.tsaheylu.DtoMapper;


import me.tsaheylu.dto.GroupDTO;
import me.tsaheylu.model.Group;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GroupDtoMapper {
//    GroupDtoMapper INSTANCE = Mappers.getMapper(GroupDtoMapper.class);

    GroupDTO toDto(Group group);

    Group dtoTo(GroupDTO groupDto);

    List<GroupDTO> toDtoList(List<Group> groupList);

    List<Group> dtoToList(List<GroupDTO> groupDTOList);
}

