package com.bdg.pc_build.product.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@MappedSuperclass
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Setter
@Getter
public abstract class Product {

    @Column(name = "name", updatable = false, unique = true)
    String name;

    @Column(name = "price", nullable = false)
    Double price;

    @Column(name = "purchased_price", nullable = false)
    Double purchasedPrice;

    @Column(name = "count", nullable = false)
    Integer count;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    Timestamp createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    Timestamp updatedAt;

    public Product(
            final String name,
            final Double price,
            final Double purchasedPrice,
            final Integer count) {
        this.name = name;
        this.price = price;
        this.purchasedPrice = purchasedPrice;
        this.count = count;
    }
}