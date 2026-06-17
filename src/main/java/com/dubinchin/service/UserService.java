package com.dubinchin.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.dubinchin.dto.UserDto;
import com.dubinchin.entity.User;
import com.dubinchin.exception.ResourceNotFoundException;
import com.dubinchin.mapper.UserMapper;
import com.dubinchin.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserDto getCurrentUser(String userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new ResourceNotFoundException("User with ID: " + userId + " not found"));

        return userMapper.toDto(user);
    }
}
