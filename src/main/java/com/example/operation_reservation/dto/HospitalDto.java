package com.example.operation_reservation.dto;

import com.example.operation_reservation.responseDto.DoctorResponseDto;

import java.util.List;

public record HospitalDto(
        String name,
        List<DoctorResponseDto> doctors
) {
}
