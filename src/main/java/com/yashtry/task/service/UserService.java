package com.yashtry.task.service;

import com.yashtry.task.dto.UserDTO;
import com.yashtry.task.enumuration.Role;
import com.yashtry.task.exception.CustomValidationException;
import com.yashtry.task.mapper.UserMapper;
import com.yashtry.task.model.User;
import com.yashtry.task.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    public void registerUser(UserDTO userDTO) {

        if(userRepository.existsByEmailIs(userDTO.getEmail())) {

            throw new CustomValidationException("Email already exsits, try another one!");
        }

        if(!isPasswordMatch(userDTO.getPassword(),userDTO.getPasswordConfirm())) {

            throw new CustomValidationException("Passwords not matched");
        }

        User user = userMapper.toUser(userDTO);

        String encodedPassword = passwordEncoder.encode(userDTO.getPassword());

        user.setPassword(encodedPassword);

        userRepository.save(user);
    }

    private Boolean isPasswordMatch(String password,String passwordConfirm) {

        return password.equals(passwordConfirm);
    }

    @PostConstruct
    public void createAdminUser() {

        if(userRepository.count() != 0) {

            return;
        }

        User user = new User();

        user.setEmail("admin@mail.com");
        user.setRole(Role.ADMIN);
        user.setPassword(passwordEncoder.encode("admin123"));

        userRepository.save(user);

    }
}
