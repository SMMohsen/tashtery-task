package com.yashtry.task.controller;

import com.yashtry.task.dto.ApiResponse;
import com.yashtry.task.dto.PictureDTO;
import com.yashtry.task.dto.UserDTO;
import com.yashtry.task.service.UserService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;

    @PostMapping
    @ApiOperation(notes = "" , value = "api for user registration"
            ,consumes = "application/json" , produces = "application/json", response = ApiResponse.class)
    public ApiResponse registerNewUser(@RequestBody @Valid UserDTO userDTO) {

        userService.registerUser(userDTO);

        return ApiResponse.ok();
    }
}
