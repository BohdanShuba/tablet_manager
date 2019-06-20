package com.diploma.tablet_manager.controller;

import com.diploma.tablet_manager.domain.Role;
import com.diploma.tablet_manager.domain.User;
import com.diploma.tablet_manager.dto.LoginDto;
import com.diploma.tablet_manager.repos.UserRepository;
import com.diploma.tablet_manager.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/registration")
public class RegistrationController {

    private final UserServiceImpl userServiceImpl;

/*
    @PostMapping("/add")
    public String addUser(LoginDto loginDto, Map<String, Object> model) {
        User userFromDb = userServiceImpl.findUserByLogin(loginDto.getLogin());
        if (userFromDb != null) {
            model.put("drugs", "User exists!");
            return "registration";
        }
        userServiceImpl.addNewUser(loginDto);
        return "redirect:/login";
    }
*/

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepo;

    @PostMapping("/add")
    public String addUser(LoginDto loginDto, Map<String, Object> model){
        User userFromDb = userServiceImpl.findUserByLogin(loginDto.getLogin());

        if (userFromDb != null) {
            model.put("drugs", "User exists!");
            return "registration";
        }

        userServiceImpl.addNewUser(loginDto);
        return "redirect:/login";

    }

}
