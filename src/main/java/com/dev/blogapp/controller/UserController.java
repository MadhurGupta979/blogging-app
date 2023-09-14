package com.dev.blogapp.controller;

import com.dev.blogapp.payloads.ApiResponse;
import com.dev.blogapp.payloads.UserDto;
import com.dev.blogapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public ResponseEntity<UserDto> saveUser(@RequestBody UserDto user) {
        UserDto userDto = this.userService.saveUser(user);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable int id) {
        UserDto userDto = this.userService.getUserById(id);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @GetMapping("/get")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> userDtos = this.userService.getAllUsers();
        return new ResponseEntity<>(userDtos, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserDto> update(@RequestBody UserDto userDto, @PathVariable int id) {
        UserDto updatedUser = this.userService.updateUser(userDto, id);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable int id) {
        this.userService.deleteUser(id);
        return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted ! id : " + id, true), HttpStatus.OK);
    }

}
