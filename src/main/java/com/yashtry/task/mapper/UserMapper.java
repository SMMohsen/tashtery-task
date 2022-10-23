package com.yashtry.task.mapper;

import com.yashtry.task.dto.UserDTO;
import com.yashtry.task.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(UserDTO userDTO);

    UserDTO toDTO(User user);


}
