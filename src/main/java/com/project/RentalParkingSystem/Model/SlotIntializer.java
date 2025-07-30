package com.project.RentalParkingSystem.Model;

import com.project.RentalParkingSystem.Repository.slotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SlotIntializer implements CommandLineRunner {

    @Autowired
    private slotRepository repo;
    @Override
    public void run(String... args) {
        if (repo.count() == 0) {
            for (int i = 1; i <=constants.TOTAL_BIKE_SPACES; i++) {
                repo.save(new slot("B" + i, "bike"));
            }
            for (int i = 1; i <= constants.TOTAL_CAR_SPACES; i++) {
                repo.save(new slot("C" + i, "car"));
            }
        }
    }
}
