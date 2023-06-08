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
 * @Author Arman Hakhverdyan
 * <p>
 * Entity for PowerSupply
 */
@Entity
@Table(name = "power_supply")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Setter
@Getter
public class PowerSupply extends Product {

    @Column(name = "efficiency_rating", nullable = false, updatable = false)
    String efficiencyRating;

    @Column(name = "wattage", nullable = false, updatable = false)
    Integer wattage;

    @Column(name = "modular", nullable = false, updatable = false)
    Boolean modular;

    @Column(name = "tdp", nullable = false, updatable = false)
    Integer tdp;

}