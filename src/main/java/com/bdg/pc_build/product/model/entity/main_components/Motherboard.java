package com.bdg.pc_build.product.model.entity.main_components;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import com.bdg.pc_build.product.model.entity.Product;

/**
 * @author Arman Hakhverdyan
 * <p>
 * Entity for Motherboard
 */

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Motherboard extends Product {
    @Column(name = "socket_cpu", nullable = false, updatable = false)
    private String socketCpu;

    @Column(name = "form_factor", nullable = false, updatable = false)
    private String formFactor;

    @Column(name = "memory_max", nullable = false, updatable = false)
    private Integer memoryMax;

    @Column(name = "memory_slots", nullable = false, updatable = false)
    private Integer memorySlots;

    @Column(name = "memory_type", nullable = false, updatable = false)
    private String memoryType;

    @Column(name = "tdp", nullable = false, updatable = false)
    private Integer tdp;

    public String getSocketCpu() {
        return socketCpu;
    }

    public void setSocketCpu(String socketCpu) {
        this.socketCpu = socketCpu;
    }

    public String getFormFactor() {
        return formFactor;
    }

    public void setFormFactor(String formFactor) {
        this.formFactor = formFactor;
    }

    public Integer getMemoryMax() {
        return memoryMax;
    }

    public void setMemoryMax(Integer memoryMax) {
        this.memoryMax = memoryMax;
    }

    public Integer getMemorySlots() {
        return memorySlots;
    }

    public void setMemorySlots(Integer memorySlots) {
        this.memorySlots = memorySlots;
    }

    public String getMemoryType() {
        return memoryType;
    }

    public void setMemoryType(String memoryType) {
        this.memoryType = memoryType;
    }

    public Integer getTdp() {
        return tdp;
    }

    public void setTdp(Integer tdp) {
        this.tdp = tdp;
    }
}
