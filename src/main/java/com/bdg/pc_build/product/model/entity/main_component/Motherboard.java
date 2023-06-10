package com.bdg.pc_build.product.model.entity.main_component;


import com.bdg.pc_build.product.model.dto.main_component.MotherboardDTO;
import com.bdg.pc_build.product.model.entity.Product;
import com.bdg.pc_build.product.model.enumerations.ATXType;
import com.bdg.pc_build.product.model.enumerations.DDRType;
import com.bdg.pc_build.product.model.enumerations.GPUInterfaceType;
import com.bdg.pc_build.product.model.enumerations.SocketType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Objects;

/**
 * @author Arman Hakhverdyan
 *
 * Entity for Motherboard
 */

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "motherboard")
public class Motherboard extends Product {

    @Column(name = "socket_type", nullable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    SocketType socketType;

    @Column(name = "is_ATX", nullable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    ATXType atxType;

    @Column(name = "memory_max", nullable = false, updatable = false)
    Integer memoryMax;

    @Column(name = "memory_slots", nullable = false, updatable = false)
    Integer memorySlots;

    @Column(name = "memory_type", nullable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    DDRType memoryType;

    @Column(name = "internal_connections", nullable = false, updatable = false)
    String internalConnections;
    
    @Column(name= "gpu_interface", nullable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    GPUInterfaceType gpuInterfaceType;

    @Column(name = "tdp", nullable = false, updatable = false)
    Integer tdp;

    public Motherboard(final MotherboardDTO dto) {
        super(dto.getName(), dto.getPrice(), dto.getPurchasedPrice(), dto.getCount());
        this.socketType = dto.getSocketType();
        this.atxType = dto.getAtxType();
        this.memoryMax = dto.getMemoryMax();
        this.memorySlots = dto.getMemorySlots();
        this.memoryType = dto.getMemoryType();
        this.internalConnections = dto.getInternalConnections();
        this.gpuInterfaceType = dto.getGpuInterfaceType();
        this.tdp = dto.getTdp();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Motherboard that = (Motherboard) o;
        return Objects.equals(socketType, that.socketType)
                && Objects.equals(atxType, that.atxType)
                && Objects.equals(memoryMax, that.memoryMax)
                && Objects.equals(memorySlots, that.memorySlots)
                && Objects.equals(memoryType, that.memoryType)
                && Objects.equals(internalConnections, that.internalConnections)
                && Objects.equals(gpuInterfaceType, that.gpuInterfaceType)
                && Objects.equals(tdp, that.tdp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(socketType, atxType, memoryMax, memorySlots, memoryType,internalConnections, gpuInterfaceType,tdp);
    }
}