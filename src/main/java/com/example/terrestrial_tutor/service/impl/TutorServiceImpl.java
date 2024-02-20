package com.example.terrestrial_tutor.service.impl;

import com.example.terrestrial_tutor.entity.TutorEntity;
import com.example.terrestrial_tutor.exceptions.UserExistException;
import com.example.terrestrial_tutor.payload.request.RegistrationRequest;
import com.example.terrestrial_tutor.repository.PupilRepository;
import com.example.terrestrial_tutor.repository.TutorRepository;
import com.example.terrestrial_tutor.security.JWTAuthenticationFilter;
import com.example.terrestrial_tutor.service.TutorService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TutorServiceImpl implements TutorService {

    public static final Logger LOG = LoggerFactory.getLogger(JWTAuthenticationFilter.class);

    @Autowired
    @NonNull
    TutorRepository tutorRepository;
    @Autowired
    @NonNull
    PupilRepository pupilRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public TutorEntity addNewTutor(RegistrationRequest userIn) {
        TutorEntity tutor = new TutorEntity();
        tutor.setEmail(userIn.getEmail());
        tutor.setName(userIn.getName());
        tutor.setSurname(userIn.getSurname());
        tutor.setPatronymic(userIn.getPatronymic());
        tutor.setUsername(userIn.getEmail());
        tutor.setPassword(passwordEncoder.encode(userIn.getPassword()));
        tutor.setRole(userIn.getRole());

        try {
            LOG.info("Saving User {}", userIn.getEmail());
            if (tutorRepository.findTutorEntityByUsername(userIn.getEmail()) != null
                    || pupilRepository.findPupilEntityByUsername(userIn.getEmail()) != null) {
                throw new UserExistException("Login " + tutor.getUsername() + "already exist");
            } else {
                return tutorRepository.save(tutor);
            }
        } catch (Exception ex) {
            LOG.error("Error during registration");
            throw new UserExistException("The Login " + tutor.getUsername() + "already exist");
        }
    }

    public void deleteTutorById(Long id) {
        tutorRepository.deleteById(id);
    }

    public TutorEntity verifyTutor(Long id) {
        TutorEntity tutor = tutorRepository.findTutorEntityById(id);
        tutor.setVerification(true);
        return tutorRepository.save(tutor);
    }

    public TutorEntity updateTutor(TutorEntity tutor) {
        return tutorRepository.save(tutor);
    }

    public TutorEntity findTutorById(Long id) { return tutorRepository.findTutorEntityById(id); }
}
