package com.example.operation_reservation.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Hospital {
    @Id
    @GeneratedValue
    private Integer id;

    private String name;

    private Patient patient;

    private Doctor doctor;

    @Column(
            updatable = false,
            nullable = false
    )
    private LocalDateTime createdAt;
    @Column(
            insertable = false
    )
    private LocalDateTime lastModified;
}
