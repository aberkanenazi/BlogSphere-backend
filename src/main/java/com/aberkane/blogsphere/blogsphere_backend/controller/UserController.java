package com.aberkane.blogsphere.blogsphere_backend.controller;


import com.aberkane.blogsphere.blogsphere_backend.dto.SignupRequestDto;
import com.aberkane.blogsphere.blogsphere_backend.dto.UserDto;
import com.aberkane.blogsphere.blogsphere_backend.services.UserTransectionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin("http://localhost:3000")
public class UserController {

    private final UserTransectionService userTransectionService;

    public UserController(UserTransectionService userTransectionService) {
        this.userTransectionService = userTransectionService;
    }

    @GetMapping("/all")
    public List<UserDto> getAllUser() {
        return userTransectionService.getAllUser();
    }

    @GetMapping("/get/{id}")
    public UserDto getById(@PathVariable Long id) {
        return userTransectionService.getUserByID(id);
    }

    @PutMapping("/update/{id}")
    public String updateUserDetails(@PathVariable Long id,@RequestBody SignupRequestDto signupRequestDto){
        return userTransectionService.updateUser(id,signupRequestDto);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id){
        return userTransectionService.deleteUser(id);
    }
}
