package com.example.operation_reservation.responseDto;

import java.util.List;

public record HospitalResponseDto(
    String name,
    List<DoctorResponseDto> doctors
) {
}
