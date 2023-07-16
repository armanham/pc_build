package com.bdg.pc_build.product.model.entity.main;

import com.bdg.pc_build.pc_builder.model.entity.Computer;
import com.bdg.pc_build.order.model.entity.Order;
import com.bdg.pc_build.product.enumerations.SocketType;
import com.bdg.pc_build.product.model.dto.main.CPUDTO;
import com.bdg.pc_build.product.model.entity.Product;
import com.bdg.pc_build.util.InitialAndFinalIdValues;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "cpu", schema = "product")
public class CPU extends Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cpu_seq")
    @SequenceGenerator(
            name = "cpu_seq", sequenceName = "cpu_sequence", schema = "product",
            initialValue = InitialAndFinalIdValues.INITIAL_ID_VALUE_CPU, allocationSize = 1
    )
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

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "cpus")
    private List<Order> orders;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cpu")
    private List<Computer> computers;

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