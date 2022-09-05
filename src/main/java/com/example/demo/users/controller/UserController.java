package com.example.demo.users.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.users.dto.UserCreateDto;
import com.example.demo.users.dto.UserDeleteDto;
import com.example.demo.users.dto.UserGetDto;
import com.example.demo.users.dto.UserResponseDto;
import com.example.demo.users.dto.UserUpdateDto;
import com.example.demo.users.entity.User;
import com.example.demo.users.service.UserService;

@RestController
@RequestMapping("api/v1/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("")
	public ResponseEntity<UserResponseDto> create(@RequestBody UserCreateDto userCreateDto) {
        UserResponseDto userResponseDto = userService.create(userCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponseDto);
	}

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> get(@PathVariable("id") Long id) {
        UserGetDto userGetDto = new UserGetDto();
        userGetDto.setId(id);

        UserResponseDto userResponseDto = userService.get(userGetDto);
        
        if (userResponseDto != null) {
            return ResponseEntity.status(HttpStatus.OK).body(userResponseDto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(userResponseDto);
        }
    }

    @GetMapping("")
    public ResponseEntity<List<UserResponseDto>> getList(@RequestParam Integer page, @RequestParam Integer size) {
        PageRequest pageRequest = PageRequest.of(page, size);

        List<UserResponseDto> result = userService.getList(pageRequest);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDto> update(@PathVariable("id") Long id, @RequestBody UserUpdateDto userUpdateDto) {
        userUpdateDto.setId(id);

        User user = userService.update(userUpdateDto);
        UserResponseDto userResponseDto = new UserResponseDto(user.getId(), user.getName(), user.getAge());

        return ResponseEntity.status(HttpStatus.OK).body(userResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserResponseDto> delete(@PathVariable("id") Long id, @RequestBody UserDeleteDto userDeleteDto) {
        userDeleteDto.setId(id);

        User user = userService.delete(userDeleteDto);
        UserResponseDto userResponseDto = new UserResponseDto(user.getId(), user.getName(), user.getAge());

        return ResponseEntity.status(HttpStatus.OK).body(userResponseDto);
    }
}
