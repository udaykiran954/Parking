package com.project.RentalParkingSystem.Controller;

import com.project.RentalParkingSystem.Model.slot;
import com.project.RentalParkingSystem.Repository.UserRepository;
import com.project.RentalParkingSystem.Repository.slotRepository;
import com.project.RentalParkingSystem.Service.UserService;
import com.project.RentalParkingSystem.Model.customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@RequestMapping("/rental")
@RestController
public class UserController {
    @Autowired
    UserService service;
    @Autowired
    slotRepository parkingSlotRepo;
    @Autowired
    UserRepository customerRepo;

    @PostMapping("/add")

    public ResponseEntity<String> enterVehicle(@RequestBody customer cust) {
        if (cust == null)
            return ResponseEntity.badRequest().body("please send me valid Details");
        String type = cust.getVehiclename().toLowerCase();

        // Check if vehicle already exists
        Optional<customer> existingCustomerOpt = customerRepo.findByVehiclenumber(cust.getVehiclenumber());

        // Check for available slot
        Optional<slot> slotOpt = parkingSlotRepo.findFirstBySlottypeAndIsoccupiedFalse(type);

        if (slotOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("No available slots for: " + type);
        }

        slot assignedSlot = slotOpt.get();
        assignedSlot.setIsoccupied(true);
        parkingSlotRepo.save(assignedSlot);

        if (existingCustomerOpt.isPresent()) {
            slot check = existingCustomerOpt.get().getAssignedsot();
            if(check.isIsoccupied())
                return ResponseEntity.badRequest().body("vehicle already exist at"+ check.getSlotcode());
            customer existing = existingCustomerOpt.get();
            existing.setAssignedsot(assignedSlot);
            existing.setEntrytime(LocalDateTime.now());
            existing.setExittime(null);
            existing.setAmount(0);
            existing.setPaid(false);
            existing.setName(cust.getName());
            existing.setPhonenumber(cust.getPhonenumber());


            customerRepo.save(existing);
            return ResponseEntity.ok("Vehicle already exists, re-parked at slot: " + assignedSlot.getSlotcode());
        } else {
            // New customer
            cust.setAssignedsot(assignedSlot);
            cust.setEntrytime(LocalDateTime.now());
            service.post(cust);
            return ResponseEntity.ok("Vehicle parked at slot: " + assignedSlot.getSlotcode());
        }
    }

    @GetMapping("/getall")
    public ResponseEntity<List<customer>> getAll() {
        List<customer> list = service.getAll();
        if (!list.isEmpty())
            return ResponseEntity.ok(list);
        else return ResponseEntity.noContent().build();
    }

    @GetMapping("/get{id}")
    public ResponseEntity<Optional<customer>> get(@PathVariable int id) {
        Optional<customer> cust = service.getByid(id);
        if (cust != null)
            return ResponseEntity.ok().body(cust);
        else
            return ResponseEntity.badRequest().body(null);
    }

    @GetMapping("/getby{number}")
    public ResponseEntity<customer> getbynumber(@PathVariable String number) {
        Optional<customer> cust = service.getBynumber(number);
        if (cust.isPresent())
            return ResponseEntity.ok().body(cust.get());
        else
            return ResponseEntity.badRequest().body(null);
    }

    @GetMapping("/search/{keyword}")
    public ResponseEntity<List<customer>> search(@PathVariable String keyword) {
        List<customer> list = service.getByKeyword(keyword);
        if (list.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(list);
    }


    @PutMapping("/update{number}")
    public ResponseEntity<customer> pickup(@PathVariable String number) {
        Optional<customer> cust = service.getBynumber(number);
        if (cust.isEmpty())
            return ResponseEntity.badRequest().body(null);
        customer user = cust.get();
        LocalDateTime exitTime = LocalDateTime.now();
        user.setExittime(exitTime);
        long duration = java.time.Duration.between(user.getEntrytime(), exitTime).toMinutes();
        double amount;
        if (user.getVehiclename().toLowerCase().equals("car"))
            amount = duration * 0.4;
        else
            amount = duration * 0.2;// Or use your custom rate logic
        user.setAmount(amount);
        return ResponseEntity.ok().body(user);
    }

    @PutMapping("/updateby")
    public ResponseEntity<String> updatedetails(@RequestBody customer cust) {
        if (cust == null || cust.getId() == 0) {
            return ResponseEntity.badRequest().body("Customer details are invalid or ID is missing.");
        }

        Optional<customer> existing = customerRepo.findById(cust.getId());
        if (existing.isEmpty()) {
            return ResponseEntity.status(404).body("Customer not found with ID: " + cust.getId());
        }

        try {
            service.update(cust);
            return ResponseEntity.ok().body("Update successful.");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Update failed due to server error.");
        }
    }
    @DeleteMapping("/deleteby{number}")
    public ResponseEntity<String> delete(@PathVariable String number) {
        Optional<customer> cust = customerRepo.findByVehiclenumber(number);

        if (cust.isEmpty()) {
            return ResponseEntity.badRequest().body("Vehicle does not exist in our database");
        }

        if (service.delete(number)) {
            return ResponseEntity.ok("Vehicle deleted successfully");
        } else {
            return ResponseEntity.status(500).body("There was an error on our server while deleting");
        }
    }
    @GetMapping("/stats")
    public Map<String, Object> getParkingStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalSlots", service.getTotalSlots());
        stats.put("vehiclesInParking", service.getVehiclesInParking());
        stats.put("vehiclesPickedUpToday", service.getVehiclesPickedUpToday());
        stats.put("availableBikeSlots", service.getAvailableBikeSlots());
        stats.put("availableCarSlots", service.getAvailableCarSlots());

        return stats;
    }
}

