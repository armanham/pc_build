package com.bdg.pc_build.product.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;


/**
 * @author Arman Hakhverdyan
 * <p>
 * Entity for RAM
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class Ram extends Product {

    @NotBlank(message = "speed must be provided")
    @Column(name = "speed", nullable = false, updatable = false)
    Integer speed;

    @NotBlank(message = "modules must be provided")
    @Column(name = "modules", nullable = false, updatable = false)
    Integer modules;

    @Column(name = "color")
    String color;

    @NotBlank(message = "firstWordLatency must be provided")
    @Column(name = "first_word_latency", nullable = false, updatable = false)
    Integer firstWordLatency;

    @NotBlank(message = "casLatency must be provided")
    @Column(name = "cas_latency", nullable = false, updatable = false)
    Integer casLatency;

    @Column(name = "rgb")
    Boolean rgb;

    public Ram() {
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getModules() {
        return modules;
    }

    public void setModules(int modules) {
        this.modules = modules;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getFirstWordLatency() {
        return firstWordLatency;
    }

    public void setFirstWordLatency(int firstWordLatency) {
        this.firstWordLatency = firstWordLatency;
    }

    public int getCasLatency() {
        return casLatency;
    }

    public void setCasLatency(int casLatency) {
        this.casLatency = casLatency;
    }

    public boolean isRgb() {
        return rgb;
    }

    public void setRgb(boolean rgb) {
        this.rgb = rgb;
    }
}