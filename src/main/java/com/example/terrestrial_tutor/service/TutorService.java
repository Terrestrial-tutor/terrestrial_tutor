package com.example.terrestrial_tutor.service;

import com.example.terrestrial_tutor.entity.TutorEntity;
import com.example.terrestrial_tutor.payload.request.RegistrationRequest;

public interface TutorService {
    TutorEntity addNewTutor(RegistrationRequest userIn);
    TutorEntity findTutorById(Long id);
    TutorEntity verifyTutor(Long id);
    void deleteTutorById(Long id);
    TutorEntity updateTutor(TutorEntity tutor);
}
