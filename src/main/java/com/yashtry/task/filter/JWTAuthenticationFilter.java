package com.yashtry.task.filter;

import com.yashtry.task.exception.CustomValidationException;
import com.yashtry.task.util.JWTUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    private final JWTUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String auth = request.getHeader("Authorization");

        if(auth == null || !auth.startsWith("Bearer ")) {

            filterChain.doFilter(request,response);
            return;
        }

        Claims claims = jwtUtil.getAllClaimsFromToken(auth.substring(7));


        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(claims.getSubject(),claims.get("password"),
                        Arrays.asList(new SimpleGrantedAuthority("ROLE_" + claims.get("role"))));

        SecurityContextHolder.getContext().setAuthentication(token);
        request.setAttribute("userEmail",claims.getSubject());

        filterChain.doFilter(request,response);
    }

}
