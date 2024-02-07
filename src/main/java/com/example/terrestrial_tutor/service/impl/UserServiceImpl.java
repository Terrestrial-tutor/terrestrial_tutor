package com.example.terrestrial_tutor.service.impl;

import com.example.terrestrial_tutor.entity.User;
import com.example.terrestrial_tutor.exceptions.UserExistException;
import com.example.terrestrial_tutor.payload.request.RegistrationRequest;
import com.example.terrestrial_tutor.repository.UserRepository;
import com.example.terrestrial_tutor.security.JWTAuthenticationFilter;
import com.example.terrestrial_tutor.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    public static final Logger LOG = LoggerFactory.getLogger(JWTAuthenticationFilter.class);

    @Autowired
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public User createUser(RegistrationRequest userIn) {
        User user = new User();
        user.setEmail(userIn.getEmail());
        user.setName(userIn.getName());
        user.setSurname(userIn.getSurname());
        user.setPatronymic(userIn.getPatronymic());
        user.setUsername(userIn.getEmail());
        user.setPassword(passwordEncoder.encode(userIn.getPassword()));
        user.setRole(userIn.getRole());

        try {
            LOG.info("Saving User {}", userIn.getEmail());
            if (userRepository.findUserByUsername(userIn.getEmail()) != null) {
                throw new UserExistException("The user " + user.getUsername() + "already exist");
            } else {
                return userRepository.save(user);
            }
        } catch (Exception ex) {
            LOG.error("Error during registration");
            throw new UserExistException("The user " + user.getUsername() + "already exist");
        }
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }

}
