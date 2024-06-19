package com.aberkane.blogsphere.blogsphere_backend.services;

import com.aberkane.blogsphere.blogsphere_backend.dto.SignupRequestDto;
import com.aberkane.blogsphere.blogsphere_backend.dto.UserDto;

import java.util.List;

public interface UserTransectionService {
    List<UserDto> getAllUser();

    UserDto getUserByID(Long id);

    String updateUser(Long id, SignupRequestDto signupRequestDto);

    String deleteUser(Long id);
}
