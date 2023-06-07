package com.bdg.pc_build.product.model.entity.display;

import com.bdg.pc_build.product.model.entity.Product;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;


@Entity
public class Monitor extends Product {
    @Column(name = "screen_type", nullable = false, updatable = false)
    private double screenSize;
    @Column(name = "refresh_rate", nullable = false, updatable = false)
    private int refreshRate;
    @Column(name = "screen_type", nullable = false, updatable = false)
    private String screenType;
}
