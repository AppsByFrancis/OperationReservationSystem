package com.example.operation_reservation.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

public class TimeTableSlot {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(
            name = "doctor_id"
    )
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(
            name = "patient_id"
    )
    private Patient patient;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Boolean isBooked;
}
