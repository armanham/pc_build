package com.bdg.pc_build.product.model.entity.peripherals;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import com.bdg.pc_build.product.model.entity.Product;

@Entity
public class Speaker extends Product {
    @Column(name = "frequency", nullable = false, updatable = false)
    private int frequency;
    @Column(name = "power_source", nullable = false, updatable = false)
    private String powerSource;
    @Column(name = "cable_length", nullable = false, updatable = false)
    private double cableLength;
    @Column(name = "dimension", nullable = false, updatable = false)
    private String dimension;

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public String getPowerSource() {
        return powerSource;
    }

    public void setPowerSource(String powerSource) {
        this.powerSource = powerSource;
    }

    public double getCableLength() {
        return cableLength;
    }

    public void setCableLength(double cableLength) {
        this.cableLength = cableLength;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }
}
