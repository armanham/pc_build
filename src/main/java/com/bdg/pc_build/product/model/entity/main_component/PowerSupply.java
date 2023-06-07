package com.bdg.pc_build.product.model.entity.main_component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import com.bdg.pc_build.product.model.entity.Product;

/**
 * @Author Arman Hakhverdyan
 * <p>
 * Entity for PowerSupply
 */
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PowerSupply extends Product {

    @Column(name = "efficiency_rating", nullable = false, updatable = false)
    private String efficiencyRating;

    @Column(name = "wattage", nullable = false, updatable = false)
    private Integer wattage;

    @Column(name = "modular", nullable = false, updatable = false)
    private Boolean modular;

    @Column(name = "tdp", nullable = false, updatable = false)
    private Integer tdp;

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

    public Integer getTdp() {
        return tdp;
    }

    public void setTdp(Integer tdp) {
        this.tdp = tdp;
    }
}

