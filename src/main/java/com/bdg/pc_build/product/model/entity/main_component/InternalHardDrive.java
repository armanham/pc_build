package com.bdg.pc_build.product.model.entity.main_component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import com.bdg.pc_build.product.model.entity.Product;

@Entity
public class InternalHardDrive extends Product {
    @Column(name = "capacity", nullable = false, updatable = false)
    private int capacity;
    @Column(name = "tdp", nullable = false, updatable = false)
    private Integer tdp;

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
