package com.bdg.pc_build.product.model.entity.main_component;


import com.bdg.pc_build.product.model.dto.main_component.MotherboardDTO;
import com.bdg.pc_build.product.model.entity.Product;
import com.bdg.pc_build.product.model.enumerations.DDRType;
import com.bdg.pc_build.product.model.enumerations.SocketType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Objects;

/**
 * @author Arman Hakhverdyan
 * <p>
 * Entity for Motherboard
 */

@Entity
@Table(name = "motherboard")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Motherboard extends Product {

    @Column(name = "socket_cpu", nullable = false, updatable = false)
    SocketType socketTypeCpu;

    @Column(name = "is_ATX", nullable = false, updatable = false)
    Boolean isATX;

    @Column(name = "memory_max", nullable = false, updatable = false)
    Integer memoryMax;

    @Column(name = "memory_slots", nullable = false, updatable = false)
    Integer memorySlots;

    @Column(name = "memory_type", nullable = false, updatable = false)
    DDRType memoryType;

    @Column(name = "tdp", nullable = false, updatable = false)
    Integer tdp;

    public Motherboard(final MotherboardDTO dto) {
        super(dto.getName(), dto.getPrice(), dto.getPurchasedPrice(), dto.getCount());
        this.socketTypeCpu = SocketType.valueOf(dto.getSocketCpu());
        this.isATX = dto.getIsATX();
        this.memoryMax = dto.getMemoryMax();
        this.memorySlots = dto.getMemorySlots();
        this.memoryType = DDRType.valueOf(dto.getMemoryType());
        this.tdp = dto.getTdp();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Motherboard that = (Motherboard) o;
        return Objects.equals(socketTypeCpu, that.socketTypeCpu)
                && Objects.equals(isATX, that.isATX)
                && Objects.equals(memoryMax, that.memoryMax)
                && Objects.equals(memorySlots, that.memorySlots)
                && Objects.equals(memoryType, that.memoryType)
                && Objects.equals(tdp, that.tdp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(socketTypeCpu, isATX, memoryMax, memorySlots, memoryType, tdp);
    }
}