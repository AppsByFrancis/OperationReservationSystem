package com.example.operation_reservation.controllers;

import com.example.operation_reservation.dto.AppointmentDto;
import com.example.operation_reservation.models.Appointment;
import com.example.operation_reservation.repositories.AppointmentRepository;
import com.example.operation_reservation.responseDto.AppointmentResponseDto;
import com.example.operation_reservation.service.AppointmentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {
    AppointmentRepository appointmentRepository;
    AppointmentService appointmentService;

    public AppointmentController(AppointmentRepository appointmentRepository, AppointmentService appointmentService)
    {
        this.appointmentRepository = appointmentRepository;
        this.appointmentService = appointmentService;
    }

    @PostMapping
    public AppointmentResponseDto addAppointment(AppointmentDto dto) throws Exception
    {
        return appointmentService.createAppointment(dto);
    }
}
