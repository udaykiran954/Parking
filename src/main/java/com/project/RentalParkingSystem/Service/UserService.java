package com.project.RentalParkingSystem.Service;

import com.project.RentalParkingSystem.Model.slot;
import com.project.RentalParkingSystem.Repository.UserRepository;
import com.project.RentalParkingSystem.Model.customer;
import com.project.RentalParkingSystem.Repository.slotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository repo;
    @Autowired
    private slotRepository slotRepo;
    public void post(customer cust) {
            repo.save(cust);
        }
        public List<customer> getAll(){
        return repo.findAll();
        }
        public Optional<customer> getByid(int id){
        return repo.findById(id);

        }
    public Optional<customer> getBynumber(String id){
        return repo.findByVehiclenumber(id);
    }
    public List<customer> getByKeyword(String keyword) {
        return repo.findByKeyword(keyword);
    }


    public boolean update(customer cust) {
        if (cust == null || cust.getId() == 0) {
            return false;
        }
        repo.save(cust);
        return true;
    }
    public boolean delete(String number) {
        Optional<customer> custom = repo.findByVehiclenumber(number);
        if (custom.isEmpty()) {
            return false;
        }
        repo.delete(custom.get()); // Get the actual entity
        return true;
    }
    public long getTotalSlots() {
        return slotRepo.count();
    }

    public long getVehiclesInParking() {
        return repo.findAll().stream()
                .filter(c -> c.getExittime() == null)
                .count();
    }

    public long getVehiclesPickedUpToday() {
        return repo.findAll().stream()
                .filter(c -> c.getExittime() != null && c.getExittime().toLocalDate().isEqual(LocalDate.now()))
                .count();
    }

    public long getAvailableBikeSlots() {
        return slotRepo.findAll().stream()
                .filter(s -> !s.isIsoccupied() && s.getSlottype().equalsIgnoreCase("bike"))
                .count();
    }

    public long getAvailableCarSlots() {
        return slotRepo.findAll().stream()
                .filter(s -> !s.isIsoccupied() && s.getSlottype().equalsIgnoreCase("car"))
                .count();
    }
   public void set(String number){
        Optional<customer> cust =  repo.findByVehiclenumber(number);
        customer user = cust.get();
        user.setPaid(true);
        user.setAssignedsot(null);
        user.setExittime(LocalDateTime.now());
        repo.save(user);

   }
}
