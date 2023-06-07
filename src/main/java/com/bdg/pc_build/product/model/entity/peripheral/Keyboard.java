package com.bdg.pc_build.product.model.entity.peripheral;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import com.bdg.pc_build.product.model.entity.Product;

@Entity
public class Keyboard extends Product {
    @Column(name = "keyboard_class", nullable = false, updatable = false)
    private String keyboardClass;
    @Column(name = "cable_length", nullable = false, updatable = false)
    private double cableLength;
    @Column(name = "dimension", nullable = false, updatable = false)
    private String dimension;
    @Column(name = "weight", nullable = false, updatable = false)
    private double weight;
}
