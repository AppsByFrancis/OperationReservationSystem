package com.example.operation_reservation.repositories;

import com.example.operation_reservation.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
}
