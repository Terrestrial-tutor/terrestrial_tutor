package com.example.terrestrial_tutor.service;

import com.example.terrestrial_tutor.entity.PupilEntity;
import com.example.terrestrial_tutor.payload.request.RegistrationRequest;

import java.security.Principal;
import java.util.List;

public interface PupilService {
    PupilEntity addNewPupil(RegistrationRequest userIn);
    PupilEntity findPupilById(Long id);
    List<PupilEntity> findPupilsByIds(List<Long> ids);
    PupilEntity verifyPupil(Long id);
    PupilEntity getCurrentPupil(Principal principal);
    PupilEntity updatePupil(PupilEntity pupil);
    List<PupilEntity> findAllPupils();

}
