package com.bdg.pc_build.product.model.entity.main_component;

import com.bdg.pc_build.product.model.dto.ProductDTO;
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
@Table(name = "gpu")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Setter
@Getter
public class GPU extends Product {
    @Column(name = "chipset", nullable = false, updatable = false)
    String chipset;

    @Column(name = "memory", nullable = false, updatable = false)
    Integer memory;

    @Column(name = "core_clock", nullable = false, updatable = false)
    Double coreClock;

    @Column(name = "boost_clock", nullable = false, updatable = false)
    Double boostClock;

    @Column(name = "length", nullable = false, updatable = false)
    Double length;

    @Column(name = "tdp", nullable = false, updatable = false)
    Integer tdp;

}