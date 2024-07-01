package com.example.operation_reservation;

import com.example.operation_reservation.models.Doctor;
import com.example.operation_reservation.models.Hospital;
import com.example.operation_reservation.models.Patient;
import com.example.operation_reservation.repositories.DoctorRepository;
import com.example.operation_reservation.repositories.HospitalRepository;
import com.example.operation_reservation.repositories.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.List;

@SpringBootApplication
public class OperationReservationApplication {

	public static void main(String[] args) {
		var ctx = SpringApplication.run(OperationReservationApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(HospitalRepository hospitalRepository, DoctorRepository doctorRepository, PatientRepository patientRepository) {
		return args -> {
			// Create Hospital
			Hospital hospital = Hospital.builder()
					.name("Nemocnice Krč")
					.build();

			hospital = hospitalRepository.save(hospital);

			// Create Doctors
			Doctor doctor1 = Doctor.builder()
					.firstName("František")
					.lastName("Majer")
					.qualification("Gynekolog")
					.hospital(hospital)
					.build();

			Doctor doctor2 = Doctor.builder()
					.firstName("Michael")
					.lastName("Azilikanon")
					.qualification("Surgeon")
					.hospital(hospital)
					.build();

			doctor1 = doctorRepository.save(doctor1);
			doctor2 = doctorRepository.save(doctor2);

			// Create Patients
			Patient patient1 = Patient.builder()
					.firstName("Alice")
					.lastName("Brown")
					.hospital(hospital)
					.doctors(List.of(doctor1, doctor2))
					.build();

			Patient patient2 = Patient.builder()
					.firstName("Bob")
					.lastName("White")
					.hospital(hospital)
					.doctors(List.of(doctor2))
					.build();

			patientRepository.save(patient1);
			patientRepository.save(patient2);
		};
	}

}
