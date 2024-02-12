package com.example.terrestrial_tutor.service;

import com.example.terrestrial_tutor.entity.AdminEntity;
import com.example.terrestrial_tutor.entity.PupilEntity;
import com.example.terrestrial_tutor.payload.request.RegistrationRequest;

public interface AdminService {
    AdminEntity addNewAdmin(RegistrationRequest userIn);
    AdminEntity findAdminById(Long id);
}
