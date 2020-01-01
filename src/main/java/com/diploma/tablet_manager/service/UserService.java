package com.diploma.tablet_manager.service;

import com.diploma.tablet_manager.domain.User;
import com.diploma.tablet_manager.dto.UserDto;

public interface UserService {
    User findUserByLogin(String login);

    User findUserByLoginOrEmail (String login, String email);

    User addNewUser(UserDto userDto);

    User getCurrentUser();
}
