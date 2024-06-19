package com.aberkane.blogsphere.blogsphere_backend.controller;


import com.aberkane.blogsphere.blogsphere_backend.dto.LoginRequestDto;
import com.aberkane.blogsphere.blogsphere_backend.dto.LoginResponseDto;
import com.aberkane.blogsphere.blogsphere_backend.dto.SignupRequestDto;
import com.aberkane.blogsphere.blogsphere_backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/api")
@CrossOrigin
public class AuthController {

    @Autowired
    UserService userService;

    @PostMapping("/signin")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {
        return ResponseEntity.ok(userService.authenticateUser(loginRequestDto));
    }

    @PostMapping("/signup")
   // @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequestDto signUpRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.addUser(signUpRequest));
    }
}
