package com.bdg.pc_build.product.model.entity.main_component;

import com.bdg.pc_build.product.model.dto.main_component.CaseDTO;
import com.bdg.pc_build.product.model.entity.Product;
import com.bdg.pc_build.product.model.enumerations.TowerType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Objects;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "a_case")
public class aCase extends Product {

    @Column(name = "max_CPU_cooler_height", nullable = false, updatable = false)
    Double maxCPUCoolerHeight;

    @Column(name = "max_GPU_length", nullable = false, updatable = false)
    Double maxGPULength;

    @Column(name = "pre_installed_fans", nullable = false, updatable = false)
    Integer preInstalledFans;

    @Column(name = "is_ATX", nullable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    TowerType towerType;

    public aCase(final CaseDTO dto) {
        super(dto.getName(), dto.getPrice(), dto.getPurchasedPrice(), dto.getCount());
        this.maxCPUCoolerHeight = dto.getMaxCPUCoolerHeight();
        this.maxGPULength = dto.getMaxGPULength();
        this.preInstalledFans = dto.getPreInstalledFans();
        this.towerType = dto.getTowerType();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        aCase aCase = (com.bdg.pc_build.product.model.entity.main_component.aCase) o;
        return Objects.equals(maxCPUCoolerHeight, aCase.maxCPUCoolerHeight)
                && Objects.equals(maxGPULength, aCase.maxGPULength)
                && Objects.equals(preInstalledFans, aCase.preInstalledFans)
                && Objects.equals(towerType, aCase.getTowerType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(maxCPUCoolerHeight, maxGPULength, preInstalledFans, towerType);
    }
}