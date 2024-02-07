package com.example.terrestrial_tutor.service;

import com.example.terrestrial_tutor.entity.PupilEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface PupilDetailsService extends UserDetailsService {
    UserDetails loadUserByUsername(String username);
    PupilEntity loadPupilById(Long id);
}
