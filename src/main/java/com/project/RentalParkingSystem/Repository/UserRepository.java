package com.project.RentalParkingSystem.Repository;

import com.project.RentalParkingSystem.Model.customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<customer, Integer> {
    @Query("SELECT c FROM customer c WHERE c.vehiclenumber = :vehiclenumber")
    Optional<customer> findByVehiclenumber(@Param("vehiclenumber") String vehiclenumber);

    @Query("SELECT c FROM customer c WHERE " +
            "LOWER(c.name) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "CAST(c.phonenumber AS string) LIKE %:keyword% OR " +
            "LOWER(c.vehiclename) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(c.vehiclenumber) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<customer> findByKeyword(@Param("keyword") String keyword);

}
