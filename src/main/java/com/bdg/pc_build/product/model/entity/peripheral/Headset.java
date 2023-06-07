package com.bdg.pc_build.product.model.entity.peripheral;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import com.bdg.pc_build.product.model.entity.Product;

@Entity
public class Headset extends Product {
    @Column(name = "frequency", nullable = false, updatable = false)
    private int frequency;
    @Column(name = "connectivity", nullable = false, updatable = false)
    private String connectivity;
    @Column(name = "cable_length", nullable = false, updatable = false)
    private double cableLength;
}
