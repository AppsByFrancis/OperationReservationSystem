package com.example.operation_reservation.dto;

import com.example.operation_reservation.models.Doctor;
import com.example.operation_reservation.models.Patient;
import com.example.operation_reservation.models.TimeTableSlot;

import java.time.LocalDateTime;

public record AppointmentDto(
        Integer doctorId,
        Integer patientId,
        String problem,
        LocalDateTime startDate
) {
}
