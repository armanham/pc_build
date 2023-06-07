package com.bdg.pc_build.product.model.entity.main_components;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import com.bdg.pc_build.product.model.entity.Product;

/**
 * @author Arman Hakhverdyan
 * <p>
 * Entity for CPU
 */
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CPU extends Product {
    @Column(name = "core_count", nullable = false, updatable = false)
    private Integer coreCount;

    @Column(name = "core_clock", nullable = false, updatable = false)
    private Integer coreClock;

    @Column(name = "boost_clock", nullable = false, updatable = false)
    private Integer boostClock;

    @Column(name = "tdp", nullable = false, updatable = false)
    private Integer tdp;

    @Column(name = "integrated_graphics", updatable = false)
    private String integratedGraphics;

    @Column(name = "socket", nullable = false, updatable = false)
    private String socket;

    public Integer getCoreCount() {
        return coreCount;
    }

    public void setCoreCount(Integer coreCount) {
        this.coreCount = coreCount;
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

    public String getSocket() {
        return socket;
    }

    public void setSocket(String socket) {
        this.socket = socket;
    }
}
