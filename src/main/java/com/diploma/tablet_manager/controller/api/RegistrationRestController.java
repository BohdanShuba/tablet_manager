package com.diploma.tablet_manager.controller.api;

import com.diploma.tablet_manager.domain.User;
import com.diploma.tablet_manager.dto.UserDto;
import com.diploma.tablet_manager.service.ConfirmationTokenService;
import com.diploma.tablet_manager.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/api/registration")
@RequiredArgsConstructor
public class RegistrationRestController {
    private final UserService userService;
    private final ConfirmationTokenService confirmationTokenService;

    @PostMapping("/add")
    public UserDto addUser(UserDto userDto) {
        log.info("Add user request: " + userDto);
        User userFromDb = userService.findUserByLoginOrEmail(userDto);
        if (userFromDb != null) {
            log.info("User not registered: " + userDto);
            return null;
        }
        UserDto response = userService.addNewUser(userDto);
        log.info("Add user response: " + userDto);
        return response;
    }

    @GetMapping(value = "/confirm-account")
    public String confirmAccount(@RequestParam("token") String confirmationToken) {
        log.info("Confirmation token request: " + confirmationToken);
        String response = confirmationTokenService.confirmUserAccount(confirmationToken);
        log.info("Confirmation token response: " + response);
        return response;
    }
}
