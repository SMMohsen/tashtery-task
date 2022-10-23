package com.yashtry.task.service;

import com.yashtry.task.model.User;
import com.yashtry.task.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<User> userOptional = userRepository.findByEmail(email);

        if(!userOptional.isPresent()) {

            throw new UsernameNotFoundException("User not found");
        }

        User user = userOptional.get();

        return new org.springframework.security.core.userdetails.User(email,user.getPassword(),
                Arrays.asList(new SimpleGrantedAuthority(user.getRole().name())));
    }
}
