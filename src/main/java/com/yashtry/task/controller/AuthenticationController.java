package com.yashtry.task.controller;

import com.yashtry.task.dto.ApiResponse;
import com.yashtry.task.dto.AuthRequest;
import com.yashtry.task.dto.PictureDTO;
import com.yashtry.task.service.AuthenticationService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    @ApiOperation(notes = "" , value = "api for authenticate users and get token"
            ,consumes = "application/json" , produces = "application/json", response = ApiResponse.class)
    public ApiResponse authenticate(@RequestBody @Valid AuthRequest authRequest) {

        return ApiResponse.ok(authenticationService.authenticateUser(authRequest));
    }
}
