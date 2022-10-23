package com.yashtry.task.mapper;

import com.yashtry.task.dto.PictureDTO;
import com.yashtry.task.dto.PictureForUserDTO;
import com.yashtry.task.dto.UploadPictureDTO;
import com.yashtry.task.model.Picture;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PictureMapper {

    Picture toPicture(UploadPictureDTO dto);

    @Mappings({
            @Mapping(source = "uploader.email",target = "uploader"),
            @Mapping(source = "name" , target = "url",qualifiedByName = "nameToUrl")
    })
    PictureDTO toDTO(Picture picture);
    @Mappings({
            @Mapping(source = "uploader.email",target = "uploader"),
            @Mapping(source = "name" , target = "url",qualifiedByName = "nameToUrl")
    })
    List<PictureDTO> toDTO(List<Picture> pics);

    @Mappings({
            @Mapping(source = "uploader.email",target = "uploader"),
            @Mapping(source = "name" , target = "url",qualifiedByName = "nameToUrl")
    })
    List<PictureForUserDTO> toPictureUserDTO(List<Picture> pictures);

    @Mappings({
            @Mapping(source = "uploader.email",target = "uploader"),
            @Mapping(source = "name" , target = "url",qualifiedByName = "nameToUrl")
    })
    PictureForUserDTO toPictureUserDTO(Picture picture);

    @Named("nameToUrl")
    static String inchToCentimeter(String name) {
        return "http://localhost:9090/" + name;
    }
}
