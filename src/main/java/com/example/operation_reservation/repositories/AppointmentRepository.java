package com.example.operation_reservation.repositories;

import com.example.operation_reservation.models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
    @Query("SELECT COUNT(a) FROM Appointment a WHERE (a.doctor.id = :doctorId OR a.patient.id = :patientId) AND (a.startDate < :endDate AND a.endDate > :startDate)")
    Long countOverlappingAppointments(@Param("doctorId") Integer doctorId, @Param("patientId") Integer patientId, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
}
