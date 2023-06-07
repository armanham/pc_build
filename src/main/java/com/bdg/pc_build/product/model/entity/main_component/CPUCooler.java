package com.bdg.pc_build.product.model.entity.main_component;

import com.bdg.pc_build.product.model.entity.Product;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "cpu_cooler")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Setter
@Getter
public class CPUCooler extends Product {
    @Column(name = "fan_RPM", nullable = false, updatable = false)
    Integer fanRPM;

    @Column(name = "socket", nullable = false, updatable = false)
    String socket;

    @Column(name = "tdp", nullable = false, updatable = false)
    Integer tdp;
}