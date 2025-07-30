package com.project.RentalParkingSystem.Controller;

import com.project.RentalParkingSystem.Model.Login;
import com.project.RentalParkingSystem.Service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth") // Enable this if you're calling from frontend like React
public class LoginController {

    @Autowired
    private LoginService loginService;
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> payload) {
        String phone = payload.get("phoneNumber");
        String bike = payload.get("bikeNumber");

        if (phone == null || bike == null) {
            return ResponseEntity.badRequest().body("Phone number and bike number are required.");
        }
        // If not found, proceed with login creation
        return loginService.login(phone, bike)
                .map(user -> ResponseEntity.ok().body("Login created successfully."))
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Invalid credentials or user not found."));
    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Login user) {
        Optional<Login> existingUser = loginService.findByBikeNumber(user.getPhoneNumber(), user.getVehicleNumber());
        if (existingUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("User already exists with your bike number and phonenumber.");
        }
        return ResponseEntity.ok(loginService.register(user));
    }
}
