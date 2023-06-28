package com.bdg.pc_build.product.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public abstract class ProductDTO {

    private Long id;
    private String name;
    private Double price;
    private Double purchasedPrice;
    private Integer count;

    public ProductDTO(
            final Long id,
            final String name,
            final Double price,
            final Double purchasedPrice,
            final Integer count
    ) {
        this.id = id;
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