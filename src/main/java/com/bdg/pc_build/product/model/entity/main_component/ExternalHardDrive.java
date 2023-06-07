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
@Table(name = "external_hard_drive")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Setter
@Getter
public class ExternalHardDrive extends Product {
    @Column(name = "type", nullable = false, updatable = false)
    String type;

    @Column(name = "capacity", nullable = false, updatable = false)
    Integer capacity;

    @Column(name = "tdp", nullable = false, updatable = false)
    Integer tdp;
}