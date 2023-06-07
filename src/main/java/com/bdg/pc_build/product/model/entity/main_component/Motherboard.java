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
 * Entity for Motherboard
 */

@Entity
@Table(name = "motherboard")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Setter
@Getter
public class Motherboard extends Product {
    @Column(name = "socket_cpu", nullable = false, updatable = false)
    String socketCpu;

    @Column(name = "form_factor", nullable = false, updatable = false)
    String formFactor;

    @Column(name = "memory_max", nullable = false, updatable = false)
    Integer memoryMax;

    @Column(name = "memory_slots", nullable = false, updatable = false)
    Integer memorySlots;

    @Column(name = "memory_type", nullable = false, updatable = false)
    String memoryType;

    @Column(name = "tdp", nullable = false, updatable = false)
    Integer tdp;
}