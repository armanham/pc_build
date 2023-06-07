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


/**
 * @author Arman Hakhverdyan
 * <p>
 * Entity for RAM
 */
@Entity
@Table(name = "ram")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Setter
@Getter
public class RAM extends Product {

    @Column(name = "speed", nullable = false, updatable = false)
    Integer speed;

    @Column(name = "count_of_RAMs", nullable = false, updatable = false)
    Integer countOfRAM;

    @Column(name = "GB_of_RAM", nullable = false, updatable = false)
    Double gbOfRAM;

    @Column(name = "tdp", nullable = false, updatable = false)
    Integer tdp;
}