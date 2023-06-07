package com.bdg.pc_build.product.model.entity.peripheral;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import com.bdg.pc_build.product.model.entity.Product;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "speaker")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Setter
@Getter
public class Speaker extends Product {
    @Column(name = "frequency", nullable = false, updatable = false)
    Integer frequency;

    @Column(name = "power_source", nullable = false, updatable = false)
    String powerSource;

    @Column(name = "cable_length", nullable = false, updatable = false)
    Double cableLength;

    @Column(name = "dimension", nullable = false, updatable = false)
    String dimension;
}