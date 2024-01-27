package com.example.terrestrial_tutor.service;

import com.example.terrestrial_tutor.entity.User;
import com.example.terrestrial_tutor.payload.request.RegistrationRequest;
import com.example.terrestrial_tutor.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public interface UserService {
    User createUser(RegistrationRequest userIn);
}
