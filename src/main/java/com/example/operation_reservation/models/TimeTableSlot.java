package com.example.operation_reservation.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class TimeTableSlot {
    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "doctor_slots")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patient_slots", nullable = false)
    private Patient patient;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Boolean isBooked;
}
