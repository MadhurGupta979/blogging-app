package com.dev.blogapp.services.impl;

import com.dev.blogapp.entities.User;
import com.dev.blogapp.exceptions.ResourceNotFoundException;
import com.dev.blogapp.payloads.UserDto;
import com.dev.blogapp.repository.UserRepo;
import com.dev.blogapp.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private ModelMapper modelMapper = new ModelMapper();
    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDto saveUser(UserDto userDto) {
        User user = this.modelMapper.map(userDto, User.class);
        User savedUser = this.userRepo.save(user);
        UserDto savedUserDto = this.modelMapper.map(savedUser, UserDto.class);
        return savedUserDto;
    }

    @Override
    public UserDto getUserById(Integer id) {
        User user = this.userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        UserDto userDto = this.modelMapper.map(user, UserDto.class);
        return userDto;
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = this.userRepo.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        users.stream().forEach((i) -> userDtos.add(this.modelMapper.map(i, UserDto.class)));
        return userDtos;
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer id) {
        User user = this.userRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        user.setEmail(userDto.getEmail());
        user.setId(userDto.getId());
        user.setAbout(userDto.getAbout());
        user.setPassword(userDto.getPassword());
        user.setName(userDto.getName());

        User savedUser = this.userRepo.save(user);
        UserDto userDto1 = this.modelMapper.map(savedUser, UserDto.class);
        return userDto1;
    }

    @Override
    public void deleteUser(Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        this.userRepo.delete(user);
    }
}
