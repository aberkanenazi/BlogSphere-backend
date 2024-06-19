package com.aberkane.blogsphere.blogsphere_backend.services;


import com.aberkane.blogsphere.blogsphere_backend.dto.LoginRequestDto;
import com.aberkane.blogsphere.blogsphere_backend.dto.LoginResponseDto;
import com.aberkane.blogsphere.blogsphere_backend.dto.SignupRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    LoginResponseDto authenticateUser(LoginRequestDto loginRequestDto);

    String addUser(SignupRequestDto signUpRequest);
}
