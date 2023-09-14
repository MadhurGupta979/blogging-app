package com.dev.blogapp.services;

import com.dev.blogapp.entities.User;
import com.dev.blogapp.payloads.UserDto;

import java.util.List;

public interface UserService {
    UserDto saveUser(UserDto userDto);

    UserDto getUserById(Integer id);

    List<UserDto> getAllUsers();

    UserDto updateUser(UserDto userDto, Integer id);

    void deleteUser(Integer userId);
}
