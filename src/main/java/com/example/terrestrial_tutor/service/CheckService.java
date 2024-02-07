package com.example.terrestrial_tutor.service;

import com.example.terrestrial_tutor.entity.CheckEntity;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface CheckService {
    List<CheckEntity> getAllChecks();

    CheckEntity addCheck(UserDetails newUser);

    CheckEntity deleteCheck(Long id);
}
