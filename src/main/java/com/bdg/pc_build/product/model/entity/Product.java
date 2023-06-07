package com.bdg.pc_build.product.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.*;
import lombok.experimental.FieldDefaults;

@MappedSuperclass
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public abstract class Product {

    @Id
    @Column(name = "name", updatable = false)
    String name;

    @Column(name = "price", nullable = false)
    Double price;

    @Column(name = "count", nullable = false)
    Integer count;
}