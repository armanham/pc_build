package com.bdg.pc_build.product.model.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.Objects;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
public abstract class ProductDTO {


    String name;
    Double price;
    Double purchasedPrice;
    Integer count;

    public ProductDTO(
            final String name,
            final Double price,
            final Double purchasedPrice,
            final Integer count
    ) {
        this.name = name;
        this.price = price;
        this.purchasedPrice = purchasedPrice;
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDTO that = (ProductDTO) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}