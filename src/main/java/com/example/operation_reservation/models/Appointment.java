package com.example.operation_reservation.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class Appointment extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "doctor_appointment")
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patient_appointment")
    private Patient patient;

    private String problem;

    @OneToOne
    @JoinColumn(name = "slot_id", nullable = false)
    private TimeTableSlot slot;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

}
