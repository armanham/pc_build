package com.bdg.pc_build.product.model.entity.main_component;

import com.bdg.pc_build.product.model.dto.main_component.CPUDTO;
import com.bdg.pc_build.product.model.entity.Product;
import com.bdg.pc_build.product.model.enumerations.SocketType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Objects;

/**
 * @author Arman Hakhverdyan
 * <p>
 * Entity for CPU
 */
@Entity
@Table(name = "cpu")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class CPU extends Product {

    @Column(name = "core_count", nullable = false, updatable = false)
    Integer coreCount;

    @Column(name = "core_clock", nullable = false, updatable = false)
    Double coreClock;

    @Column(name = "boost_clock", nullable = false, updatable = false)
    Double boostClock;

    @Column(name = "tdp", nullable = false, updatable = false)
    Integer tdp;

    @Column(name = "integrated_graphics", updatable = false)
    String integratedGraphics;

    @Column(name = "socket", nullable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    SocketType socketType;

    public CPU(final CPUDTO dto) {
        super(dto.getName(), dto.getPrice(), dto.getPurchasedPrice(), dto.getCount());
        this.coreCount = dto.getCoreCount();
        this.coreClock = dto.getCoreClock();
        this.boostClock = dto.getBoostClock();
        this.tdp = dto.getTdp();
        this.integratedGraphics = dto.getIntegratedGraphics();
        this.socketType = dto.getSocket();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CPU cpu = (CPU) o;
        return Objects.equals(coreCount, cpu.coreCount)
                && Objects.equals(coreClock, cpu.coreClock)
                && Objects.equals(boostClock, cpu.boostClock)
                && Objects.equals(tdp, cpu.tdp)
                && Objects.equals(integratedGraphics, cpu.integratedGraphics)
                && Objects.equals(socketType, cpu.socketType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(coreCount, coreClock, boostClock, tdp, integratedGraphics, socketType);
    }
}