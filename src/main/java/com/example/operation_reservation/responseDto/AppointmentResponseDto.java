package com.example.operation_reservation.responseDto;

import com.example.operation_reservation.models.Doctor;
import com.example.operation_reservation.models.Patient;

import java.time.LocalDateTime;

public record AppointmentResponseDto(
        Doctor doctor,
        Patient patient,
        String problem,
        LocalDateTime startDate,
        LocalDateTime endDate
) {
}
