package io.github.ViniciusSantos01.rest.controller;

import io.github.ViniciusSantos01.DTO.CredentialsDTO;
import io.github.ViniciusSantos01.DTO.TokenDTO;
import io.github.ViniciusSantos01.Exception.InvalidPasswordException;
import io.github.ViniciusSantos01.Implementation.UserServiceImpl;
import io.github.ViniciusSantos01.Security.JwtService;
import io.github.ViniciusSantos01.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserEntity save(@RequestBody @Valid UserEntity userEntity){
        String cryptPassaword = passwordEncoder.encode(userEntity.getPassword());
        userEntity.setPassword(cryptPassaword);
        return userService.save(userEntity);

    }

    @PostMapping("/auth")
    public TokenDTO authenticate (@RequestBody CredentialsDTO credentialsDTO){
        try {
            UserEntity user = UserEntity.builder()
                    .login(credentialsDTO.getLogin())
                    .password(credentialsDTO.getPassword())
                    .build();
            UserDetails authenticatedUser = userService.authenticate(user);
            String token = jwtService.tokenGenerator(user);
            return new TokenDTO(user.getLogin(), token);

        }catch (UsernameNotFoundException | InvalidPasswordException e){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }
}
