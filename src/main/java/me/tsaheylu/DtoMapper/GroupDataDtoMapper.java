package me.tsaheylu.DtoMapper;


import me.tsaheylu.dto.GroupDataDTO;
import me.tsaheylu.model.GroupData;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GroupDataDtoMapper {
//    GroupDataDtoMapper INSTANCE = Mappers.getMapper(GroupDataDtoMapper.class);

    GroupDataDTO toDto(GroupData GroupData);

    GroupData dtoTo(GroupDataDTO GroupDataDto);

    List<GroupDataDTO> toDtoList(List<GroupData> GroupDataList);

    List<GroupData> dtoToList(List<GroupDataDTO> GroupDataDTOList);
}

