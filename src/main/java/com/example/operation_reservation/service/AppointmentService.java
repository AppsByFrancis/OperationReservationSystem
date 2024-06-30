package com.example.operation_reservation.service;

import com.example.operation_reservation.dto.AppointmentDto;
import com.example.operation_reservation.models.Appointment;
import com.example.operation_reservation.models.Doctor;
import com.example.operation_reservation.models.Patient;
import com.example.operation_reservation.models.TimeTableSlot;
import com.example.operation_reservation.repositories.AppointmentRepository;
import com.example.operation_reservation.repositories.TimeTableSlotRepository;
import com.example.operation_reservation.responseDto.AppointmentResponseDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AppointmentService {
    AppointmentRepository appointmentRepository;

    @PersistenceContext
    EntityManager entityManager;
    public AppointmentService(AppointmentRepository appointmentRepository)
    {
        this.appointmentRepository = appointmentRepository;
    }
    public AppointmentResponseDto toAppointmentResponseDto(Appointment appointment)
    {
        return new AppointmentResponseDto(
                appointment.getDoctor(),
                appointment.getPatient(),
                appointment.getProblem(),
                appointment.getStartDate(),
                appointment.getEndDate()
        );
    }

    public AppointmentResponseDto createAppointment(AppointmentDto dto) throws Exception
    {
        LocalDateTime startDate = dto.startDate();
        LocalDateTime endDate = startDate.plusMinutes(30);  // assuming appointment length is 30 minutes

        Long overlappingCount = appointmentRepository.countOverlappingAppointments(dto.doctorId(), dto.patientId(), startDate, endDate);
        if (overlappingCount > 0) {
            throw new Exception("Patient already has an appointment within the selected time range.");
        }

        Doctor doctor = entityManager.getReference(Doctor.class, dto.doctorId());
        Patient patient = entityManager.getReference(Patient.class, dto.patientId());
        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setProblem(dto.problem());
        appointment.setStartDate(startDate);
        appointment.setEndDate(endDate);
        appointmentRepository.save(appointment);

        return toAppointmentResponseDto(appointment);
    }
}
