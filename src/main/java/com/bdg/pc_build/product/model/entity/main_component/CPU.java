package com.bdg.pc_build.product.model.entity.main_component;

import com.bdg.pc_build.order.entity.Order;
import com.bdg.pc_build.util.InitialAndFinalIdValues;
import com.bdg.pc_build.product.model.dto.main_component.CPUDTO;
import com.bdg.pc_build.product.model.entity.Product;
import com.bdg.pc_build.product.enumerations.SocketType;
import jakarta.persistence.*;
import lombok.*;


import java.util.List;
import java.util.Objects;

/**
 * @author Arman Hakhverdyan
 * <p>
 * Entity for CPU
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "cpu", schema = "product")
public class CPU extends Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "entity_seq")
    @SequenceGenerator(name = "entity_seq", sequenceName = "cpu_sequence", initialValue = InitialAndFinalIdValues.INITIAL_ID_VALUE_CPU)
    @Column(name = "id")
    private Long id;

    @Column(name = "core_count", nullable = false, updatable = false)
    private Integer coreCount;

    @Column(name = "core_clock", nullable = false, updatable = false)
    private Double coreClock;

    @Column(name = "boost_clock", nullable = false, updatable = false)
    private Double boostClock;

    @Column(name = "tdp", nullable = false, updatable = false)
    private Integer tdp;

    @Column(name = "integrated_graphics", updatable = false)
    private String integratedGraphics;

    @Column(name = "socket_type", nullable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private SocketType socketType;

    @ManyToMany(mappedBy = "cpus")
    private List<Order> orders;

    public CPU(final CPUDTO dto) {
        super(dto.getName(), dto.getPrice(), dto.getPurchasedPrice(), dto.getCount());
        this.coreCount = dto.getCoreCount();
        this.coreClock = dto.getCoreClock();
        this.boostClock = dto.getBoostClock();
        this.tdp = dto.getTdp();
        this.integratedGraphics = dto.getIntegratedGraphics();
        this.socketType = dto.getSocketType();
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