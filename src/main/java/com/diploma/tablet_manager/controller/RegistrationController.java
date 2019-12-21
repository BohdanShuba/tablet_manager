package com.diploma.tablet_manager.controller;

import com.diploma.tablet_manager.domain.User;
import com.diploma.tablet_manager.dto.UserDto;
import com.diploma.tablet_manager.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/registration")
public class RegistrationController {

    private final UserServiceImpl userServiceImpl;

    @PostMapping("/add")
    public String addUser(UserDto userDto, Map<String, Object> model) {
        User userFromDb = userServiceImpl.findUserByLoginOrEmail(userDto.getLogin(), userDto.getEmail());

        if (userFromDb != null) {
            model.put("message", "Логин или почта уже используются");
            return "registration";
        }
        userServiceImpl.addNewUser(userDto);
        return "redirect:/login";
    }
}
