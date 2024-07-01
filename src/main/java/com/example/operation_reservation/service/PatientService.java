package com.example.operation_reservation.service;

import com.example.operation_reservation.dto.PatientDto;
import com.example.operation_reservation.models.Appointment;
import com.example.operation_reservation.models.Patient;
import com.example.operation_reservation.responseDto.AppointmentResponseDto;
import com.example.operation_reservation.responseDto.DoctorResponseDto;
import com.example.operation_reservation.responseDto.PatientResponseDto;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PatientService {

    private final DoctorService doctorService;

    public PatientService(DoctorService doctorService)
    {
        this.doctorService = doctorService;
    }

    public Patient toPatient(PatientDto dto)
    {
        Patient patient = new Patient();
        patient.setFirstName(dto.firstName());
        patient.setLastName(dto.lastName());


        return patient;
    }

    public PatientResponseDto toPatientResponseDto(Patient patient)
    {
        List<DoctorResponseDto> doctors = Optional.ofNullable(patient.getDoctors()).orElse(Collections.emptyList())
                .stream()
                .map(doctorService::toDoctorResponseDto)
                .collect(Collectors.toList());

        return new PatientResponseDto(
                patient.getFirstName(),
                patient.getLastName(),
                doctors,
                patient.getAppointments()
        );
    }
}
