package com.bdg.pc_build.product.model.entity.main_component;

import com.bdg.pc_build.product.model.InitialAndMaxValues;
import com.bdg.pc_build.product.model.dto.main_component.CaseDTO;
import com.bdg.pc_build.product.model.entity.Product;
import com.bdg.pc_build.product.enumerations.TowerType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.GenericGenerator;

import java.util.Objects;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "a_case")
public class aCase extends Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "entity_seq")
    @SequenceGenerator(name = "entity_seq", sequenceName = "case_sequence", initialValue = InitialAndMaxValues.INITIAL_ID_VALUE_CASE)
    @Column(name = "id")
    private Long id;

    @Column(name = "max_cpu_cooler_height", nullable = false, updatable = false)
    Double maxCpuCoolerHeight;

    @Column(name = "max_gpu_length", nullable = false, updatable = false)
    Double maxGpuLength;

    @Column(name = "pre_installed_fans", nullable = false, updatable = false)
    Integer preInstalledFans;

    @Column(name = "tower_type", nullable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    TowerType towerType;

    public aCase(final CaseDTO dto) {
        super(dto.getName(), dto.getPrice(), dto.getPurchasedPrice(), dto.getCount());
        this.maxCpuCoolerHeight = dto.getMaxCpuCoolerHeight();
        this.maxGpuLength = dto.getMaxGpuLength();
        this.preInstalledFans = dto.getPreInstalledFans();
        this.towerType = dto.getTowerType();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        aCase aCase = (com.bdg.pc_build.product.model.entity.main_component.aCase) o;
        return Objects.equals(maxCpuCoolerHeight, aCase.maxCpuCoolerHeight)
                && Objects.equals(maxGpuLength, aCase.maxGpuLength)
                && Objects.equals(preInstalledFans, aCase.preInstalledFans)
                && Objects.equals(towerType, aCase.getTowerType());
    }

    @Override
    public int hashCode() {
        return Objects.hash(maxCpuCoolerHeight, maxGpuLength, preInstalledFans, towerType);
    }
}