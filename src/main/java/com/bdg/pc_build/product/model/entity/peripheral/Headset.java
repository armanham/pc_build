package com.bdg.pc_build.product.model.entity.peripheral;

import com.bdg.pc_build.product.model.dto.ProductDTO;
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
@Table(name = "headset")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Setter
@Getter
public class Headset extends Product {
    @Column(name = "frequency", nullable = false, updatable = false)
    Integer frequency;

    @Column(name = "connectivity", nullable = false, updatable = false)
    String connectivity;

    @Column(name = "cable_length", nullable = false, updatable = false)
    Double cableLength;

}
