package com.example.demo.users.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.users.dto.UserCreateDto;
import com.example.demo.users.dto.UserResponseDto;
import com.example.demo.users.entity.User;
import com.example.demo.users.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public UserResponseDto create(UserCreateDto userCreateDto) {
        User user = new User(userCreateDto.getName(), userCreateDto.getAge());

        user = userRepository.save(user);
        return new UserResponseDto(user.getId(), user.getName(), user.getAge());
    }
}
