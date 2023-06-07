package com.bdg.pc_build.product.model.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

/**
 * @author Arman Hakhverdyan
 * <p>
 * Entity for Motherboard
 */

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Motherboard extends Product {

    @NotBlank(message = "socket  must be provided")
    @Column(name = "socket_cpu", nullable = false, updatable = false)
    String socketCpu;

    @NotBlank(message = "chipset  must be provided")
    @Column(name = "chip_set", nullable = false, updatable = false)
    String chipset;

    @NotBlank(message = "form factor must be provided")
    @Column(name = "form_factor", nullable = false, updatable = false)
    String formFactor;

    @NotBlank(message = "memory max must be provided")
    @Column(name = "memory_max", nullable = false, updatable = false)
    Integer memoryMax;

    @NotBlank(message = "memory slots must be provided")
    @Column(name = "memory_slots", nullable = false, updatable = false)
    Integer memorySlots;

    @NotBlank(message = "memory type must be provided")
    @Column(name = "memory_type", nullable = false, updatable = false)
    String memoryType;

    @Column(name = "color", updatable = false)
    String color;

    public Motherboard() {
    }

    public String getSocketCpu() {
        return socketCpu;
    }

    public void setSocketCpu(String socketCpu) {
        this.socketCpu = socketCpu;
    }

    public String getChipset() {
        return chipset;
    }

    public void setChipset(String chipset) {
        this.chipset = chipset;
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}