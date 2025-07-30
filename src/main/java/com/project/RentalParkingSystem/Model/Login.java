package com.project.RentalParkingSystem.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


    @Entity
    public class Login {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private String name;
        private String phoneNumber;
        private String vehicleNumber;

        public Long getId() {
            return id;
        }

        public Login(Long id, String name, String phoneNumber, String bikeNumber) {
            this.id = id;
            this.name = name;
            this.phoneNumber = phoneNumber;
            this.vehicleNumber = vehicleNumber;
        }

        public Login() {
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getVehicleNumber() {
            return vehicleNumber;
        }

        public void setBikeNumber(String vehicleNumber) {
            this.vehicleNumber = vehicleNumber;
        }
    }


