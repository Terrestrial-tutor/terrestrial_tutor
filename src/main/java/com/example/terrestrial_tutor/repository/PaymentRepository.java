package com.example.terrestrial_tutor.repository;

import com.example.terrestrial_tutor.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {

    PaymentEntity findPaymentEntityById(Long id);
}
