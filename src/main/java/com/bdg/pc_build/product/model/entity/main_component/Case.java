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
@Table(name = "case")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Setter
@Getter
public class Case extends Product {

    @Column(name = "max_CPU_cooler_height", nullable = false, updatable = false)
    Integer maxCPUCoolerHeight;

    @Column(name = "max_GPU_length", nullable = false, updatable = false)
    Double maxGPULength;

    @Column(name = "pre_installed_fans", nullable = false, updatable = false)
    Integer preInstalledFans;

    @Column(name = "is_ATX", nullable = false, updatable = false)
    Boolean isATX;
}