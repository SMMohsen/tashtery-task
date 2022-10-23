package com.yashtry.task.service;

import com.yashtry.task.dto.AuthRequest;
import com.yashtry.task.exception.CustomValidationException;
import com.yashtry.task.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;

    private final JWTUtil jwtUtil;

    public String authenticateUser(AuthRequest authRequest) {

        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(authRequest.getEmail()
        ,authRequest.getPassword());

        Authentication authenticate = authenticationManager.authenticate(token);

        if(!authenticate.isAuthenticated()) {

            throw new CustomValidationException("email or password not correct");
        }

        return jwtUtil.generateToken((UserDetails) authenticate.getPrincipal());
    }
}
