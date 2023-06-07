package com.bdg.pc_build.product.model.entity.main_components;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import com.bdg.pc_build.product.model.entity.Product;

@Entity
public class GPU extends Product {
    @Column(name = "chipset", nullable = false, updatable = false)
    private String chipset;
    @Column(name = "memory", nullable = false, updatable = false)
    private int memory;
    @Column(name = "core_clock", nullable = false, updatable = false)
    private double coreClock;
    @Column(name = "boost_clock", nullable = false, updatable = false)
    private double boostClock;
    @Column(name = "length", nullable = false, updatable = false)
    private double length;
    @Column(name = "tdp", nullable = false, updatable = false)
    private Integer tdp;

    public String getChipset() {
        return chipset;
    }

    public void setChipset(String chipset) {
        this.chipset = chipset;
    }

    public int getMemory() {
        return memory;
    }

    public void setMemory(int memory) {
        this.memory = memory;
    }

    public double getCoreClock() {
        return coreClock;
    }

    public void setCoreClock(double coreClock) {
        this.coreClock = coreClock;
    }

    public double getBoostClock() {
        return boostClock;
    }

    public void setBoostClock(double boostClock) {
        this.boostClock = boostClock;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public Integer getTdp() {
        return tdp;
    }

    public void setTdp(Integer tdp) {
        this.tdp = tdp;
    }
}
