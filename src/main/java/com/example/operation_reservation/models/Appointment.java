package com.example.operation_reservation.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private Doctor doctor;

    @ManyToOne
    @JoinColumn(name = "patient_appointment")
    @JsonIgnore
    private Patient patient;

    private String problem;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

}
