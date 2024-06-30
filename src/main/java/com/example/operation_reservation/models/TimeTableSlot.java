package com.example.operation_reservation.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class TimeTableSlot extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "doctor_slots")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patient_slots", nullable = false)
    private Patient patient;

    @OneToOne(mappedBy = "slot")
    private Appointment appointment;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Boolean isBooked;
}
