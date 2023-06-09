package com.bdg.pc_build.product.model.entity.main_component;

import com.bdg.pc_build.product.model.dto.main_component.GPUDTO;
import com.bdg.pc_build.product.model.entity.Product;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Objects;

@Entity
@Table(name = "gpu")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class GPU extends Product {

    @Column(name = "chipset", nullable = false, updatable = false)
    String chipset;

    @Column(name = "memory", nullable = false, updatable = false)
    Integer memory;

    @Column(name = "core_clock", nullable = false, updatable = false)
    Double coreClock;

    @Column(name = "boost_clock", nullable = false, updatable = false)
    Double boostClock;

    @Column(name = "length", nullable = false, updatable = false)
    Double length;

    @Column(name = "tdp", nullable = false, updatable = false)
    Integer tdp;

    public GPU(final GPUDTO dto) {
        super(dto.getName(), dto.getPrice(), dto.getPurchasedPrice(), dto.getCount());
        this.chipset = dto.getChipset();
        this.memory = dto.getMemory();
        this.coreClock = dto.getCoreClock();
        this.boostClock = dto.getBoostClock();
        this.length = dto.getLength();
        this.tdp = dto.getTdp();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GPU gpu = (GPU) o;
        return Objects.equals(chipset, gpu.chipset)
                && Objects.equals(memory, gpu.memory)
                && Objects.equals(coreClock, gpu.coreClock)
                && Objects.equals(boostClock, gpu.boostClock)
                && Objects.equals(length, gpu.length)
                && Objects.equals(tdp, gpu.tdp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(chipset, memory, coreClock, boostClock, length, tdp);
    }
}