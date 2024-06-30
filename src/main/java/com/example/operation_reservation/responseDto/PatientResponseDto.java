package com.example.operation_reservation.responseDto;

import java.util.List;

public record PatientResponseDto(
        String firstName,
        String lastName,
        List<DoctorResponseDto> doctors
) {
}
