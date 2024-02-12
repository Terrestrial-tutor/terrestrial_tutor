package com.example.terrestrial_tutor.service;

import com.example.terrestrial_tutor.entity.PupilEntity;
import com.example.terrestrial_tutor.payload.request.RegistrationRequest;

public interface PupilService {
    PupilEntity addNewPupil(RegistrationRequest userIn);
    PupilEntity findPupilById(Long id);
    PupilEntity verifyPupil(Long id);
    void deletePupilById(Long id);
}
