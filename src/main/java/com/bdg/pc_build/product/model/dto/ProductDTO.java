package com.bdg.pc_build.product.model.dto;

import com.bdg.pc_build.product.model.entity.Product;
import lombok.*;
import lombok.experimental.FieldDefaults;

//@AllArgsConstructor
//@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class ProductDTO {

    String name;
    Double price;
    Double purchasedPrice;
    Integer count;

    public ProductDTO(String name, Double price, Double purchasedPrice, Integer count) {
        this.name = name;
        this.price = price;
        this.purchasedPrice = purchasedPrice;
        this.count = count;
    }

    public ProductDTO() {
    }
}