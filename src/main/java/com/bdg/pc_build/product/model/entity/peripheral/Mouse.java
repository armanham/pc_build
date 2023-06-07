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
@Table(name = "mouse")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Setter
@Getter
public class Mouse extends Product {
    @Column(name = "type", nullable = false, updatable = false)
    String type;

    @Column(name = "max_resolution", nullable = false, updatable = false)
    Integer maxResolution;

    @Column(name = "cable_length", nullable = false, updatable = false)
    Double cableLength;

    @Column(name = "weight", nullable = false, updatable = false)
    Double weight;
}
