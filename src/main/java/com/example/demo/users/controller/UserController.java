package com.example.demo.users.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.users.dto.UserCreateDto;
import com.example.demo.users.dto.UserResponseDto;
import com.example.demo.users.service.UserService;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("")
	public ResponseEntity<UserResponseDto> create(@RequestBody UserCreateDto userCreateDto) {
        UserResponseDto userResponseDto = userService.create(userCreateDto);
        return ResponseEntity.ok().body(userResponseDto);
	}
}
