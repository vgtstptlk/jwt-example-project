package com.vgtstptlk.jwtexampleproject.controller;

import com.vgtstptlk.jwtexampleproject.domain.User;
import com.vgtstptlk.jwtexampleproject.security.jwt.JwtProvider;
import com.vgtstptlk.jwtexampleproject.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController("/api")
public class AuthController {
    private UserService userService;
    private JwtProvider jwtProvider;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestParam String username, @RequestParam String password,
                                          @RequestParam String firstname, @RequestParam String lastname){
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setFirstname(firstname);
        user.setLastname(lastname);
        userService.saveUser(user);

        return ResponseEntity.ok(user);
    }

    @PostMapping("/auth")
    private ResponseEntity<?> authUser(@RequestParam String username, @RequestParam String password){
        Optional<User> optionalUser = userService.findByUsernameAndPassword(username, password);
        if (optionalUser.isPresent()){
            return ResponseEntity.ok(jwtProvider.generateToken(username));
        }

        return ResponseEntity.badRequest().body("Invalid password or login");
    }
}
