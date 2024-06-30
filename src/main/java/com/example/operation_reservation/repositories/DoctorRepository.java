package com.example.operation_reservation.repositories;

import com.example.operation_reservation.models.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Integer> {
}
