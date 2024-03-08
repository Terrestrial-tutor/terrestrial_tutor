package com.example.terrestrial_tutor.service;

import com.example.terrestrial_tutor.entity.AdminEntity;
import com.example.terrestrial_tutor.entity.TutorEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface AdminDetailsService extends UserDetailsService {
    UserDetails loadUserByUsername(String username);

    AdminEntity loadAdminById(Long id);
}
