package com.diploma.tablet_manager.controller;

import com.diploma.tablet_manager.config.jwt.JwtProvider;
import com.diploma.tablet_manager.domain.User;
import com.diploma.tablet_manager.dto.AuthorizeJwtDto;
import com.diploma.tablet_manager.dto.SignInDto;
import com.diploma.tablet_manager.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Log4j2
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthorizationController {

    private final UserService userService;
    private final JwtProvider jwtProvider;

    @PostMapping("/sign/in")
    public AuthorizeJwtDto signIn(@Valid @RequestBody SignInDto signInDto) {
        User userEntity = userService.findByLoginAndPassword(signInDto.getLogin(), signInDto.getPassword());
        String token = jwtProvider.generateToken(userEntity.getLogin());
        return new AuthorizeJwtDto(token,
                userEntity.getId(),
                userEntity.getEmail(),
                userEntity.getRole());
    }

//    @PostMapping("/sign/up")
//    public String signUp(@Valid SignInDto signInDto) {
//        String token = signInDto.toString();
//        return token;
//    }
}
