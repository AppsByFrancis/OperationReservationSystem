package com.example.operation_reservation.repositories;

import com.example.operation_reservation.models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
}
