package com.bdg.pc_build.product.model.entity.main_component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import com.bdg.pc_build.product.model.entity.Product;

@Entity
public class CPUCooler extends Product {
    @Column(name = "fan_RPM", nullable = false,updatable = false)
    private int fanRPM;
    @Column(name = "socket", nullable = false, updatable = false)
    private String socket;
    @Column(name = "tdp", nullable = false, updatable = false)
    private Integer tdp;

    public int getFanRPM() {
        return fanRPM;
    }

    public void setFanRPM(int fanRPM) {
        this.fanRPM = fanRPM;
    }

    public String getSocket() {
        return socket;
    }

    public void setSocket(String socket) {
        this.socket = socket;
    }

    public Integer getTdp() {
        return tdp;
    }

    public void setTdp(Integer tdp) {
        this.tdp = tdp;
    }
}
