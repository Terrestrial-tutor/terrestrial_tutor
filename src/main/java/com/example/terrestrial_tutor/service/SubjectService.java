package com.example.terrestrial_tutor.service;


import com.example.terrestrial_tutor.entity.SubjectEntity;
import com.example.terrestrial_tutor.entity.TutorEntity;

import java.util.List;

public interface SubjectService {
    SubjectEntity findSubjectByName(String name);
    List<TutorEntity> findSubjectTutors(String subject);
    List<SubjectEntity> getAllSubjects();
    SubjectEntity updateSubject(SubjectEntity subject);
}
