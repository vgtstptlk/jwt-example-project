package com.vgtstptlk.jwtexampleproject.service;

import com.vgtstptlk.jwtexampleproject.domain.User;
import com.vgtstptlk.jwtexampleproject.security.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public CustomUserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> optionalUser = userService.findByUsername(s);
        optionalUser.orElseThrow(
                () -> new UsernameNotFoundException(s)
        );

        return CustomUserDetails.fromUserToCustomUserDetails(optionalUser.get());
    }

}
