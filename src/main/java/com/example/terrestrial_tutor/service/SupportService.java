package com.example.terrestrial_tutor.service;

import com.example.terrestrial_tutor.entity.PupilEntity;
import com.example.terrestrial_tutor.entity.SupportEntity;
import com.example.terrestrial_tutor.payload.request.RegistrationRequest;

import java.security.Principal;
import java.util.List;

public interface SupportService {
    SupportEntity addNewSupport(RegistrationRequest userIn);
    SupportEntity findSupportById(Long id);
    List<SupportEntity> findSupportsByIds(List<Long> ids);
    SupportEntity getCurrentSupport(Principal principal);
    SupportEntity updateSupport(SupportEntity support);
    List<SupportEntity> findAllSupports();

}
