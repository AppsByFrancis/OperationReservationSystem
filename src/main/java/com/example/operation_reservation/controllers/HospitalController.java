package com.example.operation_reservation.controllers;

import com.example.operation_reservation.dto.HospitalDto;
import com.example.operation_reservation.models.Doctor;
import com.example.operation_reservation.models.Hospital;
import com.example.operation_reservation.repositories.HospitalRepository;
import com.example.operation_reservation.responseDto.DoctorResponseDto;
import com.example.operation_reservation.responseDto.HospitalResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/hospital")
public class HospitalController {

    private HospitalRepository hospitalRepository;

    public HospitalController(HospitalRepository hospitalRepository)
    {
        this.hospitalRepository = hospitalRepository;
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

    public HospitalResponseDto toHospitalResponseDto(Hospital hospital)
    {
        List<DoctorResponseDto> doctors = hospital.getDoctors()
                .stream()
                .map(this::toDoctorResponseDto)
                .collect(Collectors.toList());
        return new HospitalResponseDto(
                hospital.getName(),
                doctors
        );
    }

    @GetMapping
    public List<HospitalResponseDto> getAllHospitals()
    {
        return hospitalRepository.findAll()
                .stream()
                .map(this::toHospitalResponseDto)
                .collect(Collectors.toList());
    }
}