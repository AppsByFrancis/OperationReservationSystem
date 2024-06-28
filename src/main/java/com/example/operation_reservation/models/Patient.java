package com.example.operation_reservation.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    private String firstName;

    private String lastName;

    private String Problem;

    @OneToMany(mappedBy = "patient")
    private List<TimeTableSlot> timeSlots;

    @ManyToOne()
    @JoinColumn(name = "hospital_patients",nullable = false)
    private Hospital hospital;

    @ManyToMany
    @JoinTable(name = "patient_doctors")
    private List<Doctor> doctors;
}
