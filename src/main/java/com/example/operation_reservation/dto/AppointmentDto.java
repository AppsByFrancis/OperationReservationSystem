package com.example.operation_reservation.dto;

import java.time.LocalDateTime;

public record AppointmentDto(
        Integer doctorId,
        Integer patientId,
        String problem,
        LocalDateTime startDate
) {
}
