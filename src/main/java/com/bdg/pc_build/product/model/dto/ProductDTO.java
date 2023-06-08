package com.bdg.pc_build.product.model.dto;

import jakarta.persistence.Access;
import lombok.*;
import lombok.experimental.FieldDefaults;

//@AllArgsConstructor
//@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDTO {
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
