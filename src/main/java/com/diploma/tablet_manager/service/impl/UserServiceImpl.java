package com.diploma.tablet_manager.service.impl;

import com.diploma.tablet_manager.domain.Role;
import com.diploma.tablet_manager.domain.User;
import com.diploma.tablet_manager.dto.UserDto;
import com.diploma.tablet_manager.mapper.Mapper;
import com.diploma.tablet_manager.repos.UserRepository;
import com.diploma.tablet_manager.service.ConfirmationTokenService;
import com.diploma.tablet_manager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ConfirmationTokenService confirmationTokenService;
    private final Mapper<UserDto, User> userMapper;


    @Override
    public User findUserByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    @Override
    public User findUserByLoginOrEmail(UserDto userDto) {
        return userRepository.findByLoginOrEmailIgnoreCase(userDto.getLogin(), userDto.getEmail());
    }

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();
        User currentUser = findUserByLogin(currentUserName);
        return currentUser;
    }


    @Override
    @Transactional
    public UserDto addNewUser(UserDto userDto) {
        User user = new User(userDto.getLogin(), passwordEncoder.encode(userDto.getPassword()));
        user.setEmail(userDto.getEmail());
        user.setEnabled(false);
        user.setRole(Collections.singleton(Role.USER));
        userRepository.saveAndFlush(user);
        confirmationTokenService.sendConfirmationToken(user);
        return userMapper.toDto(user);
    }

}
