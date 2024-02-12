package com.example.terrestrial_tutor.repository;

import com.example.terrestrial_tutor.entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<AdminEntity, Long> {
    AdminEntity findAdminEntityById(Long id);

    AdminEntity findAdminEntityByUsername(String username);
}
