package com.example.terrestrial_tutor.service.impl;

import com.example.terrestrial_tutor.entity.PupilEntity;
import com.example.terrestrial_tutor.repository.PupilRepository;
import com.example.terrestrial_tutor.service.PupilService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PupilServiceImpl implements PupilService {
    @Autowired
    @NonNull
    PupilRepository pupilRepository;

    public PupilEntity addNewPupil(PupilEntity pupil) { return pupilRepository.save(pupil); }
    public PupilEntity findPupilById(Long id) { return pupilRepository.findPupilEntityById(id); }
}
