package com.bdg.pc_build.product.model.entity.main_component;

import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.dto.main_component.CaseDTO;
import com.bdg.pc_build.product.model.entity.Product;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Objects;

@Entity
@Table(name = "case")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Case extends Product {

    @Column(name = "max_CPU_cooler_height", nullable = false, updatable = false)
    Integer maxCPUCoolerHeight;

    @Column(name = "max_GPU_length", nullable = false, updatable = false)
    Double maxGPULength;

    @Column(name = "pre_installed_fans", nullable = false, updatable = false)
    Integer preInstalledFans;

    @Column(name = "is_ATX", nullable = false, updatable = false)
    Boolean isATX;

    public Case(final CaseDTO dto) {
        super(dto.getName(), dto.getPrice(), dto.getPurchasedPrice(), dto.getCount());
        this.maxCPUCoolerHeight = dto.getMaxCPUCoolerHeight();
        this.maxGPULength = dto.getMaxGPULength();
        this.preInstalledFans = dto.getPreInstalledFans();
        this.isATX = dto.getIsATX();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Case aCase = (Case) o;
        return Objects.equals(maxCPUCoolerHeight, aCase.maxCPUCoolerHeight)
                && Objects.equals(maxGPULength, aCase.maxGPULength)
                && Objects.equals(preInstalledFans, aCase.preInstalledFans)
                && Objects.equals(isATX, aCase.isATX);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maxCPUCoolerHeight, maxGPULength, preInstalledFans, isATX);
    }
}