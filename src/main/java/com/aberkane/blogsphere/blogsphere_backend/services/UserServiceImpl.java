package com.aberkane.blogsphere.blogsphere_backend.services;

import com.aberkane.blogsphere.blogsphere_backend.dto.LoginRequestDto;
import com.aberkane.blogsphere.blogsphere_backend.dto.LoginResponseDto;
import com.aberkane.blogsphere.blogsphere_backend.dto.SignupRequestDto;
import com.aberkane.blogsphere.blogsphere_backend.exceptions.UserValidationException;
import com.aberkane.blogsphere.blogsphere_backend.model.User;
import com.aberkane.blogsphere.blogsphere_backend.model.UserRoles;
import com.aberkane.blogsphere.blogsphere_backend.repository.UserRepository;
import com.aberkane.blogsphere.blogsphere_backend.security.context.RequestContext;
import com.aberkane.blogsphere.blogsphere_backend.security.helper.JwtUtil;
import com.aberkane.blogsphere.blogsphere_backend.util.Common;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    private final ModelMapper modelMapper;

    private final JwtUtil jwtUtil;

    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final RequestContext requestContext;

    public UserServiceImpl(ModelMapper modelMapper, JwtUtil jwtUtil, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder, UserRepository userRepository, RequestContext requestContext) {
        this.modelMapper = modelMapper;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.requestContext = requestContext;
    }


    @Override
    public LoginResponseDto authenticateUser(LoginRequestDto loginRequestDto) {
        Authentication authentication
                =authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail(),loginRequestDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtUtil.generateJwtToken(authentication);
    }

    @Override
    public String addUser(SignupRequestDto signUpRequest) {
        if (Boolean.FALSE.equals(Common.isValidEmail(signUpRequest.getEmail())))
            throw new UserValidationException("Please enter a valid email address");
        if (Boolean.FALSE.equals(Common.isValidPassword(signUpRequest.getPassword())))
            throw new UserValidationException("Please enter a valid password");
        userRepository.findByEmail(signUpRequest.getEmail()).ifPresent(user->{ throw new UserValidationException("User is already exist");});

        User userEntity = modelMapper.map(signUpRequest, User.class);
        userEntity.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        UserRoles userRoles = new UserRoles();
        if(!ObjectUtils.isEmpty(signUpRequest.getRole()))
         userRoles.setRoleName(signUpRequest.getRole().getRoleName().toUpperCase());
        else
            userRoles.setRoleName("USER");
        userEntity.setRole(userRoles);
        userRepository.save(userEntity);
        return "User Successfully added";
    }
}
