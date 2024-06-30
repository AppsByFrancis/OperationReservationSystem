package com.example.operation_reservation.controllers;

import com.example.operation_reservation.dto.PatientDto;
import com.example.operation_reservation.models.Doctor;
import com.example.operation_reservation.models.Patient;
import com.example.operation_reservation.repositories.PatientRepository;
import com.example.operation_reservation.responseDto.DoctorResponseDto;
import com.example.operation_reservation.responseDto.PatientResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/patient")
public class PatientController {

    public PatientRepository patientRepository;

    public PatientController(PatientRepository patientRepository)
    {
        this.patientRepository = patientRepository;
    }

    public Patient toPatient(PatientDto dto)
    {
        Patient patient = new Patient();
        patient.setFirstName(dto.firstName());
        patient.setLastName(dto.lastName());


        return patient;
    }

    public DoctorResponseDto toDoctorResponseDto(Doctor doctor)
    {
        return new DoctorResponseDto(
                doctor.getFirstName(),
                doctor.getLastName(),
                doctor.getQualification(),
                doctor.getHospital().getName()
        );
    }

    public PatientResponseDto toPatientResponseDto(Patient patient)
    {
        List<DoctorResponseDto> doctors = Optional.ofNullable(patient.getDoctors()).orElse(Collections.emptyList())
            .stream()
            .map(this::toDoctorResponseDto)
            .collect(Collectors.toList());

        return new PatientResponseDto(
                patient.getFirstName(),
                patient.getLastName(),
                doctors
        );
    }

    @PostMapping
    public PatientResponseDto addPatient(@RequestBody PatientDto dto)
    {
        Patient newPatient = toPatient(dto);
        Patient addPatient = patientRepository.save(newPatient);
        return toPatientResponseDto(addPatient);
    }

    @GetMapping
    public List<PatientResponseDto> getAllPatients()
    {
        return patientRepository.findAll()
                .stream()
                .map(this::toPatientResponseDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public PatientResponseDto getPatient (@PathVariable Integer id)
    {
        Patient findPatinet = patientRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No patient found with such id"));
        return toPatientResponseDto(findPatinet);
    }
}
