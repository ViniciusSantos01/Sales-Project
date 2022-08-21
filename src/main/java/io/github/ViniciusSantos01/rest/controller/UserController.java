package io.github.ViniciusSantos01.rest.controller;

import io.github.ViniciusSantos01.Implementation.UserServiceImpl;
import io.github.ViniciusSantos01.entity.UserEntiry;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserEntiry save(@RequestBody @Valid UserEntiry userEntiry){
        String cryptPassaword = passwordEncoder.encode(userEntiry.getPassword());
        userEntiry.setPassword(cryptPassaword);
        return userService.save(userEntiry);

    }
}
