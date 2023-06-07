package com.bdg.pc_build.product.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "external_hard_drive")
public class ExternalHardDrive extends Product {

    @Column(name = "type", nullable = false, updatable = false)
    private String type;

    @Column(name = "interface_type", nullable = false, updatable = false)
    private String interfaceType;

    @Column(name = "capacity", nullable = false, updatable = false)
    private int capacity;

    @Column(name = "price_per_gb", nullable = false, updatable = false)
    private double pricePerGb;

    @Column(name = "color", nullable = false, updatable = false)
    private String color;

    public ExternalHardDrive() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInterfaceType() {
        return interfaceType;
    }

    public void setInterfaceType(String interfaceType) {
        this.interfaceType = interfaceType;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getPricePerGb() {
        return pricePerGb;
    }

    public void setPricePerGb(double pricePerGb) {
        this.pricePerGb = pricePerGb;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}