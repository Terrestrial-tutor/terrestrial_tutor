package com.example.terrestrial_tutor.service;

import com.example.terrestrial_tutor.entity.PupilEntity;
import com.example.terrestrial_tutor.entity.SupportEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface SupportDetailsService extends UserDetailsService {
    UserDetails loadUserByUsername(String username);

    SupportEntity loadSupportById(Long id);
}
