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

/**
 * @author Arman Hakhverdyan
 * <p>
 * Entity for CPU
 */
@Entity
@Table(name = "cpu")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Setter
@Getter
public class CPU extends Product {
    @Column(name = "core_count", nullable = false, updatable = false)
    Integer coreCount;

    @Column(name = "core_clock", nullable = false, updatable = false)
    Integer coreClock;

    @Column(name = "boost_clock", nullable = false, updatable = false)
    Integer boostClock;

    @Column(name = "tdp", nullable = false, updatable = false)
    Integer tdp;

    @Column(name = "integrated_graphics", updatable = false)
    String integratedGraphics;

    @Column(name = "socket", nullable = false, updatable = false)
    String socket;
}