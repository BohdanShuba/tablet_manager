package com.diploma.tablet_manager.mapper.impl;

import com.diploma.tablet_manager.domain.User;
import com.diploma.tablet_manager.dto.UserDto;
import com.diploma.tablet_manager.mapper.Mapper;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class UserMapper implements Mapper<UserDto, User> {

    @Override
    public UserDto toDto(User user) {
        if (isNull(user)) {
            return null;
        }
        UserDto userDto = new UserDto();
        userDto.setLogin(user.getLogin());
        userDto.setPassword(user.getPassword());
        userDto.setEmail(user.getEmail());
        return userDto;
    }
}
