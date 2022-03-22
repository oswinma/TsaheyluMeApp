package me.tsaheylu.DtoMapper;


import me.tsaheylu.dto.FriendDTO;
import me.tsaheylu.model.Friend;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface FriendDtoMapper {
//    FriendDtoMapper INSTANCE = Mappers.getMapper(FriendDtoMapper.class);

    FriendDTO toDto(Friend friend);

    Friend DtoTo(FriendDTO friendDTO);
}

