package com.project.RentalParkingSystem.Repository;

import com.project.RentalParkingSystem.Model.Login;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginRepository extends JpaRepository<Login, Long> {
    Optional<Login> findByPhoneNumberAndVehicleNumber(String phoneNumber, String vehicleNumber);

}
