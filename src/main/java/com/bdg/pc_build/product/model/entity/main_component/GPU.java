package com.bdg.pc_build.product.model.entity.main_component;

import com.bdg.pc_build.product.model.dto.main_component.GPUDTO;
import com.bdg.pc_build.product.model.entity.Product;
import com.bdg.pc_build.product.enumerations.GPUInterfaceType;
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
@Table(name = "gpu")
public class GPU extends Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "entity_seq")
    @SequenceGenerator(name = "entity_seq", sequenceName = "gpu_sequence", initialValue = 1201)
    @Column(name = "id")
    private Long id;

    @Column(name = "gpu_interface_type", nullable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    GPUInterfaceType gpuInterfaceType;

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
        this.gpuInterfaceType = dto.getGpuInterfaceType();
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
        return Objects.equals(gpuInterfaceType, gpu.gpuInterfaceType)
                && Objects.equals(memory, gpu.memory)
                && Objects.equals(coreClock, gpu.coreClock)
                && Objects.equals(boostClock, gpu.boostClock)
                && Objects.equals(length, gpu.length)
                && Objects.equals(tdp, gpu.tdp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gpuInterfaceType, memory, coreClock, boostClock, length, tdp);
    }
}