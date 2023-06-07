package com.bdg.pc_build.product.model.entity.main_components;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import com.bdg.pc_build.product.model.entity.Product;

@Entity
public class Cooler extends Product {
    @Column(name = "tdp", nullable = false, updatable = false)
    private Integer tdp;
}
