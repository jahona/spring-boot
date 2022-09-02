package com.example.demo.users;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.users.entity.User;
import com.example.demo.users.service.UserService;

@SpringBootTest
public class TestUserController {
    @Autowired
    UserService userService;

    @Test
    public void create() {
        User user = userService.create("홍길동", 26);
        
        assertEquals("홍길동", user.getName());
        assertEquals(26, user.getAge());
    }
}