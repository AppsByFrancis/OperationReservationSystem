package com.example.operation_reservation.responseDto;

import com.example.operation_reservation.models.Appointment;

import java.util.List;

public record PatientResponseDto(
        String firstName,
        String lastName,
        List<DoctorResponseDto> doctors,
        List<Appointment> appointments
) {
}
