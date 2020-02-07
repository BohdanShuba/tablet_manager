package com.diploma.tablet_manager.service;

import com.diploma.tablet_manager.domain.User;
import com.diploma.tablet_manager.dto.UserDto;

public interface UserService {
    User findUserByLogin(String login);

    User findUserByLoginOrEmail(UserDto userDto);

    UserDto addNewUser(UserDto userDto);

    User getCurrentUser();
}
