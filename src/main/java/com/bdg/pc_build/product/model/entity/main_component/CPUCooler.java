package com.bdg.pc_build.product.model.entity.main_component;

import com.bdg.pc_build.product.model.dto.main_component.CPUCoolerDTO;
import com.bdg.pc_build.product.model.entity.Product;
import com.bdg.pc_build.product.enumerations.SocketType;
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
@Table(name = "cpu_cooler")
public class CPUCooler extends Product {

    @Column(name = "fan_rpm", nullable = false, updatable = false)
    Integer fanRpm;

    @Column(name = "socket_type", nullable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    SocketType socketType;

    @Column(name = "tdp", nullable = false, updatable = false)
    Integer tdp;

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