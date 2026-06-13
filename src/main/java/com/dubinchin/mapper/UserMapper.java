package com.dubinchin.mapper;

import org.springframework.stereotype.Component;

import com.dubinchin.dto.UserDto;
import com.dubinchin.entity.User;

@Component
public class UserMapper {
    public UserDto toDto(User user) {
        if (user == null) {
            return null;
        }

        return UserDto.builder()
            .id(user.getId())
            .email(user.getEmail())
            .name(user.getName())
            .role(user.getName())
            .build();
    }
}
