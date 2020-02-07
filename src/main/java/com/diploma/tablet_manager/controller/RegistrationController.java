package com.diploma.tablet_manager.controller;

import com.diploma.tablet_manager.domain.User;
import com.diploma.tablet_manager.dto.UserDto;
import com.diploma.tablet_manager.service.ConfirmationTokenService;
import com.diploma.tablet_manager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/registration")
public class RegistrationController {

    private final UserService userService;
    private final ConfirmationTokenService confirmationTokenService;

    @PostMapping("/add")
    public String addUser(UserDto userDto, Map<String, Object> model) {
        User userFromDb = userService.findUserByLoginOrEmail(userDto);

        if (userFromDb != null) {
            model.put("message", "Логин или почта уже используются");
            return "registration";
        }
        userService.addNewUser(userDto);
        return "redirect:/login";
    }
    @GetMapping("/confirm-account")
    public String confirmAccount(@RequestParam("token")String confirmationToken, Map<String, Object> model)
    {
        String message = confirmationTokenService.confirmUserAccount(confirmationToken);
        model.put("message", message);

        return "аccountVerification";
    }
}
