package com.project.RentalParkingSystem.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class customerOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
   private String name;
    private String vehicleNumber;
    private long phoneNumber;
    private double amount;
    private String orderStatus;
    private String razorpayOrderId;

    public customerOrder(long id, String razorpayOrderId, String orderStatus, double amount, long phoneNumber, String vehicleNumber, String name) {
        this.id = id;
        this.razorpayOrderId = razorpayOrderId;
        this.orderStatus = orderStatus;
        this.amount = amount;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.vehicleNumber = vehicleNumber;
    }



    public customerOrder() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getRazorpayOrderId() {
        return razorpayOrderId;
    }

    public void setRazorpayOrderId(String razorpayOrderId) {
        this.razorpayOrderId = razorpayOrderId;
    }
}
