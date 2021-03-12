package com.vgtstptlk.jwtexampleproject.service;

import com.vgtstptlk.jwtexampleproject.domain.Role;
import com.vgtstptlk.jwtexampleproject.domain.User;
import com.vgtstptlk.jwtexampleproject.exception.RoleNotFoundException;
import com.vgtstptlk.jwtexampleproject.repository.RoleRepository;
import com.vgtstptlk.jwtexampleproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public User saveUser(User user){
        Optional<Role> optionalRole = roleRepository.findByName("ROLE_USER");
        optionalRole.orElseThrow(
                () -> new RoleNotFoundException("ROLE_USER")
        );

        user.setRole(optionalRole.get());
        user.setPassword(passwordEncoder.encode(passwordEncoder.encode(user.getPassword())));
        return userRepository.save(user);
    }

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }
}
