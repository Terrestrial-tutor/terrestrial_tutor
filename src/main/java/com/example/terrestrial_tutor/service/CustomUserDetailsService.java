package com.example.terrestrial_tutor.service;

import com.example.terrestrial_tutor.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface CustomUserDetailsService extends UserDetailsService {
    UserDetails loadUserByUsername(String username);
    User loadUserById(Long id);
}
