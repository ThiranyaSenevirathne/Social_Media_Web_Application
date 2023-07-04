package com.foodies.foodiesbackend.service.impl;

import com.foodies.foodiesbackend.DTO.ApiResponse;
import com.foodies.foodiesbackend.DTO.LoginDTO;
import com.foodies.foodiesbackend.DTO.UserDto;
import com.foodies.foodiesbackend.entity.User;
import com.foodies.foodiesbackend.repository.UserRepository;
import com.foodies.foodiesbackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public User addUser(UserDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setAge(userDto.getAge());
        user.setGender(userDto.getGender());
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        userRepository.save(user);
        return user;
    }

    @Override
    public ApiResponse login(LoginDTO loginDto) {
        User user = userRepository.findByUsername(loginDto.getUsername());
        if(user == null) {
            return new ApiResponse(404,0, "User does not exist.", false);
        }
        String password = loginDto.getPassword();
        String encodedPassword = user.getPassword();
        boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);
        if(!isPwdRight){
            return new ApiResponse(13,0, "Password mismatch", false);
        }
        int id = user.getId();
        return new ApiResponse(200, id, "Login Success", true);
    }

    @Override
    public Optional<User> getProfileData(int id) {
        Optional<User> user = userRepository.findById(id);
        return user;
    }

    @Override
    public ApiResponse updateUser(int id, UserDto userDto) {
        Optional<User> user = userRepository.findById(id);
        if(user == null) {
            return new ApiResponse(404,"User does not exist.", false);
        }else {
            User updateUser = user.get();
            updateUser.setFirstName(userDto.getFirstName());
            updateUser.setLastName(userDto.getLastName());
            updateUser.setAge(userDto.getAge());
            updateUser.setGender(userDto.getGender());
            updateUser.setEmail(userDto.getEmail());
            updateUser.setUsername(userDto.getUsername());

            userRepository.save(updateUser);
            return new ApiResponse(200,"User updated successfully", false);
        }
    }
}
