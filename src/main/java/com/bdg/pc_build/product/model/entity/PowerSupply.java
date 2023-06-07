package com.bdg.pc_build.product.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

/**
 * @Author Arman Hakhverdyan
 * <p>
 * Entity for PowerSupply
 */
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PowerSupply extends Product {

    @NotBlank(message = "Form factor must be provided")
    @Column(name = "form_factor", nullable = false, updatable = false)
    String formFactor;

    @NotBlank(message = "Efficiency rating must be provided")
    @Column(name = "efficiency_rating", nullable = false, updatable = false)
    String efficiencyRating;

    @NotBlank(message = "Wattage must be provided")
    @Column(name = "wattage", nullable = false, updatable = false)
    Integer wattage;

    @NotBlank(message = "Modular must be provided")
    @Column(name = "modular", nullable = false, updatable = false)
    Boolean modular;

    @Column(name = "color")
    String color;

    @NotBlank(message = "TDP must be provided")
    @Column(name = "tdp", nullable = false, updatable = false)
    Integer tdp;

    public PowerSupply() {
    }

    public String getFormFactor() {
        return formFactor;
    }

    public void setFormFactor(String formFactor) {
        this.formFactor = formFactor;
    }

    public String getEfficiencyRating() {
        return efficiencyRating;
    }

    public void setEfficiencyRating(String efficiencyRating) {
        this.efficiencyRating = efficiencyRating;
    }

    public Integer getWattage() {
        return wattage;
    }

    public void setWattage(Integer wattage) {
        this.wattage = wattage;
    }

    public Boolean getModular() {
        return modular;
    }

    public void setModular(Boolean modular) {
        this.modular = modular;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getTdp() {
        return tdp;
    }

    public void setTdp(Integer tdp) {
        this.tdp = tdp;
    }
}