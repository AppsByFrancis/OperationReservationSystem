package com.example.operation_reservation.controllers;

import com.example.operation_reservation.dto.DoctorDto;
import com.example.operation_reservation.models.Doctor;
import com.example.operation_reservation.repositories.DoctorRepository;
import com.example.operation_reservation.responseDto.DoctorResponseDto;
import com.example.operation_reservation.service.DoctorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    private final DoctorRepository doctorRepository;
    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService, DoctorRepository doctorRepository) {
        this.doctorService = doctorService;
        this.doctorRepository = doctorRepository;
    }

    @GetMapping
    public ResponseEntity<Object> getAllDoctors() {
        try
        {
            List<DoctorResponseDto> response = doctorRepository.findAll()
                    .stream()
                    .map(doctorService::toDoctorResponseDto)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>("An internal server error occured", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getDoctor(@PathVariable Integer id)
    {
        try
        {
            Doctor findDoctor = doctorRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No doctor found whit such id"));
            DoctorResponseDto response = doctorService.toDoctorResponseDto(findDoctor);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (ResponseStatusException e)
        {
            return new ResponseEntity<>("Resource not found, invalid id", HttpStatus.NOT_FOUND);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>("An internal server error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping
    public ResponseEntity<Object> addDoctor(@RequestBody DoctorDto dto) {
        try
        {
            Doctor doctor = doctorService.toDoctor(dto);
            Doctor savedDoctor = doctorRepository.save(doctor);
            DoctorResponseDto response = doctorService.toDoctorResponseDto(savedDoctor);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        catch (ResponseStatusException e)
        {
            return new ResponseEntity<>("Resource couldn't be created, check if firstName " +
            ",lastName and qualification have been included in request body",
                    HttpStatus.BAD_REQUEST
            );
        }
        catch (Exception e)
        {
            return new ResponseEntity<>("An internal server error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
