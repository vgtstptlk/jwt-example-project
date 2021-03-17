package com.vgtstptlk.jwtexampleproject.service;

import com.vgtstptlk.jwtexampleproject.domain.Role;
import com.vgtstptlk.jwtexampleproject.domain.User;
import com.vgtstptlk.jwtexampleproject.exception.RoleNotFoundException;
import com.vgtstptlk.jwtexampleproject.exception.UserExistsException;
import com.vgtstptlk.jwtexampleproject.exception.UserNotFoundException;
import com.vgtstptlk.jwtexampleproject.repository.RoleRepository;
import com.vgtstptlk.jwtexampleproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public User saveUser(User user){
        Optional<User> optionalUser = userRepository.findByUsername(user.getUsername());
        if (optionalUser.isPresent()){
            throw new UserExistsException(user.getUsername());
        }

        Optional<Role> optionalRole = roleRepository.findByName("ROLE_USER");
        optionalRole.orElseThrow(
                () -> new RoleNotFoundException("ROLE_USER")
        );

        user.setRole(optionalRole.get());
        user.setPassword(passwordEncoder.encode(passwordEncoder.encode(user.getPassword())));
        return userRepository.save(user);
    }

    public Optional<User> findByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public Optional<User> findByUsernameAndPassword(String username, String password){
        Optional<User> optionalUser = findByUsername(username);
        optionalUser.orElseThrow(
                () -> new UserNotFoundException(username)
        );

        User user = optionalUser.get();
        if (passwordEncoder.matches(password, user.getPassword())){
            return Optional.of(user);
        }

        return Optional.empty();
    }

    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }
}
