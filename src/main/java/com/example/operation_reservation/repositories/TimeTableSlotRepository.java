package com.example.operation_reservation.repositories;

import com.example.operation_reservation.models.TimeTableSlot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeTableSlotRepository extends JpaRepository<TimeTableSlot, Integer> {
}
