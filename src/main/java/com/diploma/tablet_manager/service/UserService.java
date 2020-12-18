package com.diploma.tablet_manager.service;

import com.diploma.tablet_manager.domain.User;
import com.diploma.tablet_manager.dto.SignUpDto;
import com.diploma.tablet_manager.dto.UserDto;

public interface UserService {
    User findUserByLogin(String login);

    User findUserByLoginOrEmail(UserDto userDto);

    UserDto addNewUser(UserDto userDto);
    void registerNewUser(SignUpDto signUpDto);

    User getCurrentUser();

    User findByLoginAndPassword(String login, String password);
}
