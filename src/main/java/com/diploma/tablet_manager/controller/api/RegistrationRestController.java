package com.diploma.tablet_manager.controller.api;

import com.diploma.tablet_manager.domain.User;
import com.diploma.tablet_manager.dto.DrugDto;
import com.diploma.tablet_manager.dto.UserDto;
import com.diploma.tablet_manager.service.ConfirmationTokenService;
import com.diploma.tablet_manager.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/api/registration")
@RequiredArgsConstructor
@Api(value = "Registration", description = "Operations pertaining to registration and authentication")
public class RegistrationRestController {
    private final UserService userService;
    private final ConfirmationTokenService confirmationTokenService;

    @ApiOperation(value = "Add new user", response = UserDto.class)
    @PostMapping("/add")
    public UserDto addUser(@ApiParam(value = "Drug object store in database table") @RequestBody UserDto userDto) {
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

    @ApiOperation(value = "Get an account confirmation", response = String.class)
    @GetMapping(value = "/confirm-account")
    public String confirmAccount(@ApiParam(value = "String confirming account") @RequestParam("token") String confirmationToken) {
        log.info("Confirmation token request: " + confirmationToken);
        String response = confirmationTokenService.confirmUserAccount(confirmationToken);
        log.info("Confirmation token response: " + response);
        return response;
    }
}
