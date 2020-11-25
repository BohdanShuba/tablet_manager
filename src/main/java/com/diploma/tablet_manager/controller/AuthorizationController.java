package com.diploma.tablet_manager.controller;

import com.diploma.tablet_manager.config.jwt.JwtProvider;
import com.diploma.tablet_manager.domain.User;
import com.diploma.tablet_manager.dto.SignInDto;
import com.diploma.tablet_manager.dto.TokenDto;
import com.diploma.tablet_manager.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Log4j2
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthorizationController {

    private final UserService userService;
    private final JwtProvider jwtProvider;

    @PostMapping("/sign/in")
    public TokenDto signIn(@Valid @RequestBody SignInDto signInDto) {
        User userEntity = userService.findByLoginAndPassword(signInDto.getLogin(), signInDto.getPassword());
        String token = jwtProvider.generateToken(userEntity.getLogin());
        return new TokenDto(token);
    }

//    @PostMapping("/sign/up")
//    public String signUp(@Valid SignInDto signInDto) {
//        String token = signInDto.toString();
//        return token;
//    }
}
