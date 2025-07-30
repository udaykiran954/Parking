package com.project.RentalParkingSystem.Service;

import com.project.RentalParkingSystem.Model.Login;
import com.project.RentalParkingSystem.Repository.LoginRepository;
import com.project.RentalParkingSystem.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LoginService {

    @Autowired
    private LoginRepository userRepo;

    public Optional<Login> login(String phone, String bike) {
        return userRepo.findByPhoneNumberAndVehicleNumber(phone, bike);
    }

    public Login register(Login login) {
        return userRepo.save(login);
    }

    public Optional<Login> findByBikeNumber(String bike,String phone) {
        return userRepo.findByPhoneNumberAndVehicleNumber(phone, bike);
    }

}

