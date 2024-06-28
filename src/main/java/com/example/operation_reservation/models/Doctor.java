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
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String Qualification;

    @OneToMany(mappedBy = "doctor")
    private List<TimeTableSlot> timeSlots;

    @ManyToOne
    @JoinColumn(name = "hospital_doctors",nullable = false)
    private Hospital hospital;

    @ManyToMany(mappedBy = "doctors")
    private List<Patient> patient;
}
