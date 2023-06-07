package com.bdg.pc_build.product.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "internal_hard_drive")
public class InternalHardDrive extends Product{

    @Column(name = "capacity", nullable = false, updatable = false)
    private Integer capacity;

    @Column(name = "price_gb", nullable = false, updatable = false)
    private Double priceGb;

    @Column(name = "type", nullable = false, updatable = false)
    private String type;

    @Column(name = "cache", nullable = false, updatable = false)
    private Integer cache;

    @Column(name = "form_factor", nullable = false, updatable = false)
    private String formFactor;

    @Column(name = "interface_type", nullable = false, updatable = false)
    private String interfaceType;

    public InternalHardDrive() {
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public double getPriceGb() {
        return priceGb;
    }

    public void setPriceGb(double priceGb) {
        this.priceGb = priceGb;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCache() {
        return cache;
    }

    public void setCache(int cache) {
        this.cache = cache;
    }

    public String getFormFactor() {
        return formFactor;
    }

    public void setFormFactor(String formFactor) {
        this.formFactor = formFactor;
    }

    public String getInterfaceType() {
        return interfaceType;
    }

    public void setInterfaceType(String interfaceType) {
        this.interfaceType = interfaceType;
    }
}