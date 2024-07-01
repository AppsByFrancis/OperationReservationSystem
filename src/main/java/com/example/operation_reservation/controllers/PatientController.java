package com.example.operation_reservation.controllers;

import com.example.operation_reservation.dto.PatientDto;
import com.example.operation_reservation.models.Patient;
import com.example.operation_reservation.repositories.PatientRepository;
import com.example.operation_reservation.responseDto.PatientResponseDto;
import com.example.operation_reservation.service.PatientService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/patient")
public class PatientController {

    private final PatientRepository patientRepository;
    private final PatientService patientService;

    public PatientController(PatientRepository patientRepository, PatientService patientService)
    {
        this.patientRepository = patientRepository;
        this.patientService = patientService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Object> addPatient(@RequestBody PatientDto dto)
    {
        try
        {
            Patient newPatient = patientService.toPatient(dto);
            Patient addPatient = patientRepository.save(newPatient);
            return new ResponseEntity<>(patientService.toPatientResponseDto(addPatient), HttpStatus.CREATED);
        }
        catch (ResponseStatusException e)
        {
            return new ResponseEntity<>("Resource couldn't be created, check if firstName " +
                    "and lastName have been included in request body",
                    HttpStatus.BAD_REQUEST
                    );
        }
        catch (Exception e)
        {
            return new ResponseEntity<>("An internal server error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> getAllPatients()
    {
        try
        {
            List<PatientResponseDto> response = patientRepository.findAll()
                    .stream()
                    .map(patientService::toPatientResponseDto)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>("An internal server error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> getPatient (@PathVariable Integer id)
    {
        try
        {
            Patient findPatinet = patientRepository.findById(id)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No patient found with such id"));
            return new ResponseEntity<>(patientService.toPatientResponseDto(findPatinet), HttpStatus.NOT_FOUND);
        }
        catch (ResponseStatusException e)
        {
            return new ResponseEntity<>("No patient found with such id", HttpStatus.NOT_FOUND);
        }
        catch (Exception e)
        {
            return  new ResponseEntity<>("An internal server error occurred", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
