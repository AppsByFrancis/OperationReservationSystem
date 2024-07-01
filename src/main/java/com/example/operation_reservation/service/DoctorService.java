package com.example.operation_reservation.service;

import com.example.operation_reservation.dto.DoctorDto;
import com.example.operation_reservation.models.Doctor;
import com.example.operation_reservation.models.Hospital;
import com.example.operation_reservation.repositories.HospitalRepository;
import com.example.operation_reservation.responseDto.DoctorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class DoctorService {

    private final HospitalRepository hospitalRepository;

    public DoctorService(HospitalRepository hospitalRepository) {
        this.hospitalRepository = hospitalRepository;
    }

    public Doctor toDoctor(DoctorDto dto) {
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

    public DoctorResponseDto toDoctorResponseDto(Doctor doctor) {
        return new DoctorResponseDto(
                doctor.getFirstName(),
                doctor.getLastName(),
                doctor.getQualification(),
                doctor.getHospital().getName()
        );
    }
}
