package com.project.RentalParkingSystem.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class slot {
    private boolean isoccupied;
    private String slotcode;
    private String slottype;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    public slot() {
    }
    public slot(String slotcode, String slottype) {
        this.slotcode = slotcode;
        this.slottype = slottype;
    }
    public slot(String slotcode, boolean isoccupied, String slottype, Long id) {
        this.slotcode = this.slotcode;
        this.isoccupied = isoccupied;
        this.slottype = slottype;
        this.id = id;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isIsoccupied() {
        return isoccupied;
    }

    public void setIsoccupied(boolean isoccupied) {
        this.isoccupied = isoccupied;
    }

    public String getSlotcode() {
        return slotcode;
    }

    public void setSlotnum(String slotcode) {
        this.slotcode = slotcode;
    }

    public String getSlottype() {
        return slottype;
    }

    public void setSlottype(String slottype) {
        this.slottype = slottype;
    }
}
