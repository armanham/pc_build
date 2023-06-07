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
@Table(name = "keyboard")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Setter
@Getter
public class Keyboard extends Product {
    @Column(name = "keyboard_class", nullable = false, updatable = false)
    String keyboardClass;

    @Column(name = "cable_length", nullable = false, updatable = false)
    Double cableLength;

    @Column(name = "dimension", nullable = false, updatable = false)
    String dimension;

    @Column(name = "weight", nullable = false, updatable = false)
    Double weight;
}
