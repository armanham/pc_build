package com.bdg.pc_build.product.model.entity.main_components;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import com.bdg.pc_build.product.model.entity.Product;


/**
 * @author Arman Hakhverdyan
 *
 * Entity for RAM
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
public class RAM extends Product {

     @Column(name = "speed",nullable = false, updatable = false)
     private Integer speed;

     @Column(name = "count_of_RAMs")
     private Integer countOfRAM;

     @Column(name = "GB_of_RAM")
     private Double gbOfRAM;

     @Column(name = "tdp")
     private Integer tdp;

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Integer getCountOfRAM() {
        return countOfRAM;
    }

    public void setCountOfRAM(Integer countOfRAM) {
        this.countOfRAM = countOfRAM;
    }

    public Double getGbOfRAM() {
        return gbOfRAM;
    }

    public void setGbOfRAM(Double gbOfRAM) {
        this.gbOfRAM = gbOfRAM;
    }

    public Integer getTdp() {
        return tdp;
    }

    public void setTdp(Integer tdp) {
        this.tdp = tdp;
    }
}
