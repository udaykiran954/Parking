package com.project.RentalParkingSystem.Controller;

import com.project.RentalParkingSystem.Model.customerOrder;
import com.project.RentalParkingSystem.Service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    PaymentService service;

    @PostMapping("/post")
    public ResponseEntity<customerOrder> post(@RequestBody customerOrder order) throws Exception {
        customerOrder created = service.createorder(order);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PostMapping("/update")
    public ResponseEntity<customerOrder> updateOrder(@RequestParam Map<String, String> response) throws Exception {
        customerOrder order = service.update(response);
        return ResponseEntity.ok().body(order);
    }
}
