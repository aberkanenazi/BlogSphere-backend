package com.aberkane.blogsphere.blogsphere_backend.dto;

import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String email;
    private String phoneNumber;
}
