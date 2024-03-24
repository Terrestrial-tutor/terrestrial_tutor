package com.example.terrestrial_tutor.service;

import com.example.terrestrial_tutor.entity.SubjectEntity;
import com.example.terrestrial_tutor.entity.TutorEntity;
import com.example.terrestrial_tutor.payload.request.RegistrationRequest;

import java.util.List;

public interface TutorService {
    TutorEntity addNewTutor(RegistrationRequest userIn);

    TutorEntity findTutorById(Long id);

    TutorEntity verifyTutor(Long id);

    void deleteTutorById(Long id);

    List<SubjectEntity> findTutorSubjectsByTutorId(Long id);

    TutorEntity updateTutor(TutorEntity tutor);

    TutorEntity addTutorSubject(TutorEntity tutor, SubjectEntity subject);
}
