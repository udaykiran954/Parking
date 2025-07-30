package com.project.RentalParkingSystem.Repository;

import com.project.RentalParkingSystem.Model.customerOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface orderRepository extends JpaRepository<customerOrder,Integer> {
    public customerOrder findByRazorpayOrderId(String orderId);
}
