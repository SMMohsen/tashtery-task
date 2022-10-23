package com.yashtry.task;

import com.yashtry.task.dto.UserDTO;
import com.yashtry.task.exception.CustomValidationException;
import com.yashtry.task.mapper.UserMapper;
import com.yashtry.task.model.User;
import com.yashtry.task.repository.UserRepository;
import com.yashtry.task.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;

public class UserTest {

    private UserRepository userRepository = mock(UserRepository.class);

    private UserService userService;

    public UserTest() {

        userService = new UserService(userRepository,new BCryptPasswordEncoder(), Mappers.getMapper(UserMapper.class));

    }


    @Test
    public void GivenAlreadyStoredEmailShouldThrowExceptionCreateNewUserTest() {

        Mockito.when(userRepository.existsByEmailIs(any(String.class))).thenReturn(true);

        Assertions.assertThrows(CustomValidationException.class,() -> userService.registerUser(getUserDTOWithMatchedPassword()),"Email already exsits, try another one!");
    }

    @Test
    public void GivenNotStoredEmailShouldSuccessCreateNewUserTest() {

        Mockito.when(userRepository.existsByEmailIs(any(String.class))).thenReturn(false);

        Assertions.assertDoesNotThrow(()->userService.registerUser(getUserDTOWithMatchedPassword()));
    }

    @Test
    public void GivenPasswordsNotMatchedShouldThrowExceptionCreateNewUserTest() {

        Mockito.when(userRepository.existsByEmailIs(any(String.class))).thenReturn(false);

        Assertions.assertThrows(CustomValidationException.class,
                ()->userService.registerUser(getUserDTOWithMisMatchedPassword()),
                "Passwords not matched");
    }


    private User getUser() {

        User user = new User();

        return user;
    }

    private UserDTO getUserDTOWithMatchedPassword() {

        UserDTO dto = new UserDTO();

        dto.setEmail("any@mail.com");
        dto.setPassword("password");
        dto.setPasswordConfirm("password");

        return dto;
    }

    private UserDTO getUserDTOWithMisMatchedPassword() {

        UserDTO dto = new UserDTO();

        dto.setEmail("any@mail.com");
        dto.setPassword("password");
        dto.setPasswordConfirm("password123");

        return dto;
    }
}
