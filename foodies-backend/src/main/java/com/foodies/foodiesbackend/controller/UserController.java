package com.foodies.foodiesbackend.controller;


import com.foodies.foodiesbackend.DTO.ApiResponse;
import com.foodies.foodiesbackend.DTO.LoginDTO;
import com.foodies.foodiesbackend.DTO.UserDto;
import com.foodies.foodiesbackend.entity.User;
import com.foodies.foodiesbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/save")
    public User addUser(@RequestBody UserDto userDto) {
        return userService.addUser(userDto);
    }

    @PostMapping("/login")
    public ApiResponse login(@RequestBody LoginDTO loginDto){
        return userService.login(loginDto);
    }

    @GetMapping("/profile/{id}")
    public Optional<User> getUserProfile(@PathVariable int id) {
        return userService.getProfileData(id);
    }

    @PatchMapping(value= "/profile/{id}")
    public ApiResponse updateUser(@PathVariable int id, @RequestBody UserDto userDto) {
        return userService.updateUser(id, userDto);
    }
}
