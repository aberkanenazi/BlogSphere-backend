package com.aberkane.blogsphere.blogsphere_backend.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CommonBackendConfig {

    @Bean
    public ModelMapper modelMapper(){
        return  new ModelMapper();
    }
}
