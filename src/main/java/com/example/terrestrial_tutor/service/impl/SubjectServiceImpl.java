package com.example.terrestrial_tutor.service.impl;

import com.example.terrestrial_tutor.entity.SubjectEntity;
import com.example.terrestrial_tutor.exceptions.CustomException;

import com.example.terrestrial_tutor.entity.PupilEntity;
import com.example.terrestrial_tutor.entity.SubjectEntity;
import com.example.terrestrial_tutor.entity.TutorEntity;
import com.example.terrestrial_tutor.repository.SubjectRepository;
import com.example.terrestrial_tutor.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    SubjectRepository subjectRepository;

    @Override
    public SubjectEntity findSubjectByName(String name) {
        return subjectRepository.findSubjectEntityByName(name);
    }

    @Override
    public SubjectEntity addSubject(String name, int count_level) {
        return null;
    }

    public SubjectEntity updateSubject(SubjectEntity subject) {
        return subjectRepository.save(subject);
    }

    public List<SubjectEntity> getAllSubjects() {
        return subjectRepository.findAll();
    }

    public SubjectEntity addSubject(String name, int count_level){
        SubjectEntity newSubject = new SubjectEntity();
        newSubject.setCountLevel(count_level);
        newSubject.setName(name);
        return subjectRepository.save(newSubject);
    }

    public List<TutorEntity> findSubjectTutors(String subject) {
        return subjectRepository.findSubjectEntityByName(subject).getTutors();
    }

}
