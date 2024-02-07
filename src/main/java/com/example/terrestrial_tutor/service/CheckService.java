package com.example.terrestrial_tutor.service;

import com.example.terrestrial_tutor.entity.CheckEntity;
import com.example.terrestrial_tutor.entity.User;

import java.util.List;

public interface CheckService {
    List<CheckEntity> getAllChecks();

    CheckEntity addCheck(User newUser);

    CheckEntity deleteCheck(Long id);
}
