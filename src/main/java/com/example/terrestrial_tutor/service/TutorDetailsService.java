package com.example.terrestrial_tutor.service;

import com.example.terrestrial_tutor.entity.TutorEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface TutorDetailsService extends UserDetailsService {
    UserDetails loadUserByUsername(String username);
    TutorEntity loadTutorById(Long id);
}
