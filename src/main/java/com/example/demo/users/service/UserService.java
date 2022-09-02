package com.example.demo.users.service;

import org.springframework.stereotype.Service;

import com.example.demo.users.entity.User;

@Service
public class UserService {
    public User create(String name, Integer age) {
        return new User(name, age);
    }
}
