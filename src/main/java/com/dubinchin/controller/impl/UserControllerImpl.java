package com.dubinchin.controller.impl;

import org.springframework.web.bind.annotation.RestController;

import com.dubinchin.controller.UserController;
import com.dubinchin.dto.UserDto;
import com.dubinchin.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserControllerImpl implements UserController {
    private final UserService userService;

    @Override
    public UserDto getCurrentUser(String userId) {
        return userService.getCurrentUser(userId);
    }
}
