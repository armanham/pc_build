package com.bdg.pc_build.product.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

/**
 * @author Arman Hakhverdyan
 * <p>
 * Entity for CPU
 */

@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Cpu extends Product {

    @NotBlank(message = "core count must be provided")
    @Column(name = "core_count", nullable = false, updatable = false)
    Integer coreCount;

    @Column(name = "rating")
    Integer rating;

    @Column(name = "rating_count")
    Integer ratingCount;

    @NotBlank(message = "core clock must be provided")
    @Column(name = "core_clock", nullable = false, updatable = false)
    Integer coreClock;

    @NotBlank(message = "boost clock must be provided")
    @Column(name = "boost_clock", nullable = false, updatable = false)
    Integer boostClock;

    @NotBlank(message = "tdp must be provided")
    @Column(name = "tdp", nullable = false, updatable = false)
    Integer tdp;

    @Column(name = "integrated_graphics", updatable = false)
    String integratedGraphics;

//    @NotBlank(message = "socket must be provided")
//    @Column(name = "socket", nullable = false, updatable = false)
//    String socket;

    @NotBlank(message = "smt must be provided")
    @Column(name = "smt", nullable = false, updatable = false)
    String smt;

    public Cpu() {
    }

    public Integer getCoreCount() {
        return coreCount;
    }

    public void setCoreCount(Integer coreCount) {
        this.coreCount = coreCount;
    }

    public void setRating(Integer rating){
        this.rating = rating;
    }

    public void setRatingCount(Integer ratingCount){
        this.ratingCount = ratingCount;
    }

    public Integer getRating(){
        return rating;
    }

    public Integer getRatingCount(){
        return ratingCount;
    }

    public Integer getCoreClock() {
        return coreClock;
    }

    public void setCoreClock(Integer coreClock) {
        this.coreClock = coreClock;
    }

    public Integer getBoostClock() {
        return boostClock;
    }

    public void setBoostClock(Integer boostClock) {
        this.boostClock = boostClock;
    }

    public Integer getTdp() {
        return tdp;
    }

    public void setTdp(Integer tdp) {
        this.tdp = tdp;
    }

    public String getIntegratedGraphics() {
        return integratedGraphics;
    }

    public void setIntegratedGraphics(String integratedGraphics) {
        this.integratedGraphics = integratedGraphics;
    }

//    public String getSocket() {
//        return socket;
//    }
//
//    public void setSocket(String socket) {
//        this.socket = socket;
//    }

    public String getSmt() {
        return smt;
    }

    public void setSmt(String smt) {
        this.smt = smt;
    }
}