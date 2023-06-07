package com.bdg.pc_build.product.model.entity.main_components;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import com.bdg.pc_build.product.model.entity.Product;

@Entity
public class ExternalHardDrive extends Product {
    @Column(name = "type", nullable = false, updatable = false)
    private String type;
    @Column(name = "capacity", nullable = false, updatable = false)
    private int capacity;
    @Column(name = "tdp", nullable = false, updatable = false)
    private Integer tdp;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Integer getTdp() {
        return tdp;
    }

    public void setTdp(Integer tdp) {
        this.tdp = tdp;
    }
}
