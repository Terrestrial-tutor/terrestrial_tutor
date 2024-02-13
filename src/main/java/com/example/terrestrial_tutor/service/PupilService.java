package com.example.terrestrial_tutor.service;

import com.example.terrestrial_tutor.entity.PupilEntity;
import com.example.terrestrial_tutor.entity.SubjectEntity;
import com.example.terrestrial_tutor.payload.request.RegistrationRequest;

import java.security.Principal;
import java.util.List;

public interface PupilService {
    PupilEntity addNewPupil(RegistrationRequest userIn);
    PupilEntity findPupilById(Long id);
    PupilEntity verifyPupil(Long id);
    PupilEntity getCurrentPupil(Principal principal);
    PupilEntity addSubjects(Principal principal, List<SubjectEntity> subjects);
    void deletePupilById(Long id);
}
