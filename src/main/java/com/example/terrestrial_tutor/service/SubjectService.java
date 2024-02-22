package com.example.terrestrial_tutor.service;

import com.example.terrestrial_tutor.entity.SubjectEntity;

public interface SubjectService {

    SubjectEntity findSubjectByName(String name);

    SubjectEntity addSubject(String name, int count_level);
}
