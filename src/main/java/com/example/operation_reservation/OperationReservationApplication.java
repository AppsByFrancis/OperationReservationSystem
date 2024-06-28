package com.example.operation_reservation;

import com.example.operation_reservation.models.Hospital;
import com.example.operation_reservation.repositories.HospitalRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class OperationReservationApplication {

	public static void main(String[] args) {
		var ctx = SpringApplication.run(OperationReservationApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(
			HospitalRepository repository
	){
		return args -> {
			var hospital = Hospital.builder()
					.name("Nemocnice Krč")
					.createdAt(LocalDateTime.now())
					.build();
			repository.save(hospital);
		};
	}

}
