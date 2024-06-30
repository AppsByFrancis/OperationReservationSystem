package com.example.operation_reservation.dto;

import com.example.operation_reservation.models.Hospital;

public record DoctorDto(
        String firstName,
        String lastName,
        String qualification
) {
}
