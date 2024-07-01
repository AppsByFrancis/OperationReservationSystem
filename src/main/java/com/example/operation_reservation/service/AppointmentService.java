package com.example.operation_reservation.service;

import com.example.operation_reservation.dto.AppointmentDto;
import com.example.operation_reservation.models.Appointment;
import com.example.operation_reservation.models.Doctor;
import com.example.operation_reservation.models.Patient;
import com.example.operation_reservation.repositories.AppointmentRepository;
import com.example.operation_reservation.repositories.DoctorRepository;
import com.example.operation_reservation.repositories.PatientRepository;
import com.example.operation_reservation.responseDto.AppointmentResponseDto;
import com.example.operation_reservation.responseDto.DoctorResponseDto;
import com.example.operation_reservation.responseDto.PatientResponseDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AppointmentService {
    AppointmentRepository appointmentRepository;
    DoctorRepository doctorRepository;
    PatientRepository patientRepository;
    DoctorService doctorService;
    PatientService patientService;

    @PersistenceContext
    EntityManager entityManager;
    public AppointmentService(DoctorRepository doctorRepository, PatientRepository patientRepository, DoctorService doctorService, PatientService patientService, AppointmentRepository appointmentRepository)
    {
        this.appointmentRepository = appointmentRepository;
        this.doctorService = doctorService;
        this.patientService = patientService;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }
    public AppointmentResponseDto toAppointmentResponseDto(Appointment appointment)
    {
        DoctorResponseDto doctor = doctorService.toDoctorResponseDto(appointment.getDoctor());
        PatientResponseDto patient = patientService.toPatientResponseDto(appointment.getPatient());
        return new AppointmentResponseDto(
                doctor,
                patient,
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
        doctor.getAppointments().add(appointment);
        patient.getAppointments().add(appointment);

        doctorRepository.save(doctor);
        patientRepository.save(patient);
        appointmentRepository.save(appointment);

        return toAppointmentResponseDto(appointment);
    }
}
