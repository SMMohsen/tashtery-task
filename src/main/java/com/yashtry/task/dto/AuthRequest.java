package com.yashtry.task.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class AuthRequest {

    @Email
    private String email;

    @NotBlank
    private String password;
}
