package com.example.operation_reservation.repositories;

import com.example.operation_reservation.models.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HospitalRepository extends JpaRepository<Hospital, Integer> {
}
