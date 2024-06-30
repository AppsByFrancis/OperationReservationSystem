package com.example.operation_reservation.controllers;

import com.example.operation_reservation.dto.DoctorDto;
import com.example.operation_reservation.models.Doctor;
import com.example.operation_reservation.models.Hospital;
import com.example.operation_reservation.repositories.DoctorRepository;
import com.example.operation_reservation.repositories.HospitalRepository;
import com.example.operation_reservation.responseDto.DoctorResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    private final HospitalRepository hospitalRepository;
    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorController(HospitalRepository hospitalRepository, DoctorRepository doctorRepository) {
        this.hospitalRepository = hospitalRepository;
        this.doctorRepository = doctorRepository;
    }

    private Doctor toDoctor(DoctorDto dto) {
        Doctor doctor = new Doctor();
        doctor.setFirstName(dto.firstName());
        doctor.setLastName(dto.lastName());
        doctor.setQualification(dto.qualification());

        // Fetch the first hospital from the database
        Hospital hospital = hospitalRepository.findAll().stream().findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No hospital found"));
        doctor.setHospital(hospital);
        return doctor;
    }

    private DoctorResponseDto toDoctorResponseDto(Doctor doctor) {
        return new DoctorResponseDto(
                doctor.getFirstName(),
                doctor.getLastName(),
                doctor.getQualification(),
                doctor.getHospital().getName()
        );
    }

    @GetMapping
    public List<DoctorResponseDto> getAllDoctors() {
        return doctorRepository.findAll()
                .stream()
                .map(this::toDoctorResponseDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public DoctorResponseDto getDoctor(@PathVariable Integer id)
    {
        Doctor findDoctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No doctor found whit such id"));
        return toDoctorResponseDto(findDoctor);
    }


    @PostMapping
    public DoctorResponseDto addDoctor(@RequestBody DoctorDto dto) {
        Doctor doctor = toDoctor(dto);
        Doctor savedDoctor = doctorRepository.save(doctor);
        return toDoctorResponseDto(savedDoctor);
    }
}
