package com.project.RentalParkingSystem.Repository;

import com.project.RentalParkingSystem.Model.slot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface slotRepository extends JpaRepository<slot, Long> {
    Optional<slot>   findFirstBySlottypeAndIsoccupiedFalse(String slottype);

}
