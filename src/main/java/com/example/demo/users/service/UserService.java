package com.example.demo.users.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.demo.users.dto.UserCreateDto;
import com.example.demo.users.dto.UserDeleteDto;
import com.example.demo.users.dto.UserGetDto;
import com.example.demo.users.dto.UserResponseDto;
import com.example.demo.users.dto.UserUpdateDto;
import com.example.demo.users.entity.User;
import com.example.demo.users.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User create(User user) {
        return userRepository.save(user);
    }

    public UserResponseDto get(UserGetDto userGetDto) {
        Long id = userGetDto.getId();
        Optional<User> optionalUser = userRepository.findById(id);
        
        User user;
        UserResponseDto userResponseDto = null;
        if (optionalUser.isPresent()) {
            /*
             * Optional get() 함수 사용시 User 클래스의 Default Constructor 필요
             */
            user = optionalUser.get();
            userResponseDto = new UserResponseDto(user.getId(), user.getName(), user.getAge());
        }

        return userResponseDto;
    }

    public List<UserResponseDto> getList(PageRequest pageRequest) {
        Page<User> users = userRepository.findAll(pageRequest);
        List<UserResponseDto> result = 
            users.stream()
                .map(user -> new UserResponseDto(user.getId(), user.getName(), user.getAge()))
                .collect(Collectors.toList());
            
        return result;
    }

    public User update(UserUpdateDto userUpdateDto) {
        Long id = userUpdateDto.getId();
        Optional<User> optionalUser = userRepository.findById(id);

        User user = null;
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
            user.setName(userUpdateDto.getName());
            user.setAge(userUpdateDto.getAge());
            userRepository.save(user);
        }

        return user;
    }

    public User delete(UserDeleteDto userDeleteDto) {
        Long id = userDeleteDto.getId();
        Optional<User> optionalUser = userRepository.findById(id);

        User user = null;
        if (optionalUser.isPresent()) {
            user = optionalUser.get();
            userRepository.delete(user);
        }

        return user;
    }
}
