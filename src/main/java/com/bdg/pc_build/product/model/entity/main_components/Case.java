package com.bdg.pc_build.product.model.entity.main_components;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import com.bdg.pc_build.product.model.entity.Product;

@Entity
public class Case extends Product {
    @Column(name = "max_CPU_cooler_height", nullable = false, updatable = false)
    private int maxCPUCoolerHeight;
    @Column(name = "max_GPU_length", nullable = false, updatable = false)
    private double maxGPULength;
    @Column(name = "pre_installed_fans", nullable = false, updatable = false)
    private int preInstalledFans;
    @Column(name = "is_ATX", nullable = false, updatable = false)
    private boolean isATX;

    public int getMaxCPUCoolerHeight() {
        return maxCPUCoolerHeight;
    }

    public void setMaxCPUCoolerHeight(int maxCPUCoolerHeight) {
        this.maxCPUCoolerHeight = maxCPUCoolerHeight;
    }

    public double getMaxGPULength() {
        return maxGPULength;
    }

    public void setMaxGPULength(double maxGPULength) {
        this.maxGPULength = maxGPULength;
    }

    public int getPreInstalledFans() {
        return preInstalledFans;
    }

    public void setPreInstalledFans(int preInstalledFans) {
        this.preInstalledFans = preInstalledFans;
    }

    public boolean isATX() {
        return isATX;
    }

    public void setATX(boolean ATX) {
        isATX = ATX;
    }
}
