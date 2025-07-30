package com.project.RentalParkingSystem.Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    private String vehiclenumber;
    private String vehiclename;
    private String name;
    public customer(int id, String vehiclenumber, String vehiclename, String name, long phonenumber, LocalDateTime entrytime, LocalDateTime exittime, slot assignedsot, double amount, boolean isPaid) {
        this.id = id;
        this.vehiclenumber = vehiclenumber;
        this.vehiclename = vehiclename;
        this.name = name;
        this.phonenumber = phonenumber;
        this.entrytime = entrytime;
        this.exittime = exittime;
        Assignedsot = assignedsot;
        this.amount = amount;
        this.isPaid = isPaid;
    }

    private long phonenumber;
    private LocalDateTime entrytime;
    private LocalDateTime exittime;
    @ManyToOne(fetch = FetchType.EAGER)
    private slot Assignedsot;
    private double amount;
    private boolean isPaid = false;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVehiclename() {
        return vehiclename;
    }

    public customer() {
    }

    public void setVehiclename(String vehiclename) {
        this.vehiclename = vehiclename;
    }

    public String getVehiclenumber() {
        return vehiclenumber;
    }

    public void setVehiclenumber(String vehiclenumber) {
        this.vehiclenumber = vehiclenumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(long phonenumber) {
        this.phonenumber = phonenumber;
    }

    public LocalDateTime getEntrytime() {
        return entrytime;
    }

    public void setEntrytime(LocalDateTime entrytime) {
        this.entrytime = entrytime;
    }

    public LocalDateTime getExittime() {
        return exittime;
    }

    public void setExittime(LocalDateTime exittime) {
        this.exittime = exittime;
    }

    public slot getAssignedsot() {
        return Assignedsot;
    }

    public void setAssignedsot(slot assignedsot) {
        Assignedsot = assignedsot;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }
}
