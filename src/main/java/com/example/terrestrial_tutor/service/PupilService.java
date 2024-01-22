package com.example.terrestrial_tutor.service;

import com.example.terrestrial_tutor.entity.PupilEntity;

public interface PupilService {
    PupilEntity addNewPupil(PupilEntity pupil);
    PupilEntity findPupilById(Long id);
}
