package com.example.operation_reservation.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
public class Hospital extends BaseEntity {
    private String name;

    @OneToMany(mappedBy = "hospital")
    private List<Patient> patients;

    @OneToMany(mappedBy = "hospital")
    private List<Doctor> doctors;
}
