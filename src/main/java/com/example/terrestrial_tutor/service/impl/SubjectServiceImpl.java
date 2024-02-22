package com.example.terrestrial_tutor.service.impl;

import com.example.terrestrial_tutor.entity.SubjectEntity;
import com.example.terrestrial_tutor.exceptions.CustomException;
import com.example.terrestrial_tutor.repository.SubjectRepository;
import com.example.terrestrial_tutor.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {
    @Autowired
    SubjectRepository subjectRepository;

    public SubjectEntity findSubjectByName(String name){
        return subjectRepository.findSubjectEntityByName(name);
    }

    public SubjectEntity addSubject(String name, int count_level){
        if(subjectRepository.findSubjectEntityByName(name) != null)
            throw new CustomException("Such an item already exists");
        SubjectEntity newSubject = new SubjectEntity();
        newSubject.setName(name);
        newSubject.setCountLevel(count_level);
        subjectRepository.save(newSubject);
        return newSubject;
    }

}
