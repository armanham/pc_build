package com.bdg.pc_build.product.model.entity.main;

import com.bdg.pc_build.pc_builder.model.entity.Computer;
import com.bdg.pc_build.order.model.entity.Order;
import com.bdg.pc_build.product.enumerations.SocketType;
import com.bdg.pc_build.product.model.dto.main.CPUCoolerDTO;
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
@Table(name = "cpu_cooler", schema = "product")
public class CPUCooler extends Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cpu_cooler_seq")
    @SequenceGenerator(
            name = "cpu_cooler_seq", sequenceName = "cpu_cooler_sequence", schema = "product",
            initialValue = InitialAndFinalIdValues.INITIAL_ID_VALUE_CPU_COOLER, allocationSize = 1
    )
    @Column(name = "id")
    private Long id;

    @Column(name = "fan_rpm", nullable = false, updatable = false)
    private Integer fanRpm;

    @Column(name = "socket_type", nullable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private SocketType socketType;

    @Column(name = "tdp", nullable = false, updatable = false)
    private Integer tdp;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "cpuCoolers")
    private List<Order> orders;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "cpuCooler")
    List<Computer> computers;

    public CPUCooler(final CPUCoolerDTO dto) {
        super(dto.getName(), dto.getPrice(), dto.getPurchasedPrice(), dto.getCount());
        this.fanRpm = dto.getFanRpm();
        this.socketType = dto.getSocketType();
        this.tdp = dto.getTdp();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CPUCooler cpuCooler = (CPUCooler) o;
        return Objects.equals(fanRpm, cpuCooler.fanRpm)
                && Objects.equals(socketType, cpuCooler.socketType)
                && Objects.equals(tdp, cpuCooler.tdp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fanRpm, socketType, tdp);
    }
}