package com.bdg.pc_build.product.model.entity.peripherals;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import com.bdg.pc_build.product.model.entity.Product;

@Entity
public class Mouse extends Product {
    @Column(name = "type", nullable = false, updatable = false)
    private String type;
    @Column(name = "max_resolution", nullable = false, updatable = false)
    private int maxResolution;
    @Column(name = "cable_length", nullable = false, updatable = false)
    private double cableLength;
    @Column(name = "weight", nullable = false, updatable = false)
    private double weight;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getMaxResolution() {
        return maxResolution;
    }

    public void setMaxResolution(int maxResolution) {
        this.maxResolution = maxResolution;
    }

    public double getCableLength() {
        return cableLength;
    }

    public void setCableLength(double cableLength) {
        this.cableLength = cableLength;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }
}
