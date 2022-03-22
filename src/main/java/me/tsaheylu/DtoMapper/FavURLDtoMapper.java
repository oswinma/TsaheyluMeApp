package me.tsaheylu.DtoMapper;


import me.tsaheylu.dto.FavURLDTO;
import me.tsaheylu.model.FavURL;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FavURLDtoMapper {
    //    FavURLDtoMapper INSTANCE = Mappers.getMapper(FavURLDtoMapper.class);

    //    @Mapping(source = "urlInfo.url", target = "url")
//    @Mapping(source = "urlInfo.title", target = "title")
//    @Mapping(source = "urlInfo.icon", target = "icon")
//    @Mapping(source = "urlInfo.host", target = "host")
//    @Mapping(source = "user.nickname", target = "nickname")
//    @Mapping(source = "user.avatarURL", target = "avatarURL")
//    @Mapping(source = "urlInfo.share", target = "share")
//    @Mapping(source = "urlInfo.favs", target = "favs")
//    @Mapping(source = "favURL.id", target = "id")
//    @Mapping(source = "favURL.status", target = "status")
//    default FavURLDTO toDto(FavURL favURL) {
//
//    }

    FavURL DtoTo(FavURLDTO favURLDTO);

//    List<FavURLDTO> toDtoList(List<FavURL> favURLList) {
//
//    }

    List<FavURL> dtoToList(List<FavURLDTO> favURLDTOList);
}

