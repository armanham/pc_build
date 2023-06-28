package com.bdg.pc_build.product.model.entity.main_component;


import com.bdg.pc_build.order.model.entity.Order;
import com.bdg.pc_build.product.enumerations.ATXType;
import com.bdg.pc_build.product.enumerations.DDRType;
import com.bdg.pc_build.product.enumerations.GPUInterfaceType;
import com.bdg.pc_build.product.enumerations.SocketType;
import com.bdg.pc_build.product.model.dto.main_component.MotherboardDTO;
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
@Table(name = "motherboard", schema = "product")
public class Motherboard extends Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "motherboard_seq")
    @SequenceGenerator(
            name = "motherboard_seq", sequenceName = "motherboard_sequence", schema = "product",
            initialValue = InitialAndFinalIdValues.INITIAL_ID_VALUE_MOTHERBOARD, allocationSize = 1
    )
    @Column(name = "id")
    private Long id;

    @Column(name = "socket_type", nullable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private SocketType socketType;

    @Column(name = "atx_type", nullable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private ATXType atxType;

    @Column(name = "memory_max", nullable = false, updatable = false)
    private Integer memoryMax;

    @Column(name = "memory_slots", nullable = false, updatable = false)
    private Integer memorySlots;

    @Column(name = "ddr_type", nullable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private DDRType ddrType;

    @Column(name = "is_m2", nullable = false, updatable = false)
    private Boolean isM2;

    @Column(name = "gpu_interface_type", nullable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private GPUInterfaceType gpuInterfaceType;

    @Column(name = "tdp", nullable = false, updatable = false)
    private Integer tdp;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "motherboards")
    private List<Order> orders;

    public Motherboard(final MotherboardDTO dto) {
        super(dto.getName(), dto.getPrice(), dto.getPurchasedPrice(), dto.getCount());
        this.socketType = dto.getSocketType();
        this.atxType = dto.getAtxType();
        this.memoryMax = dto.getMemoryMax();
        this.memorySlots = dto.getMemorySlots();
        this.ddrType = dto.getDdrType();
        this.isM2 = dto.getIsM2();
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
                && Objects.equals(ddrType, that.ddrType)
                && Objects.equals(isM2, that.isM2)
                && Objects.equals(gpuInterfaceType, that.gpuInterfaceType)
                && Objects.equals(tdp, that.tdp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(socketType, atxType, memoryMax, memorySlots, ddrType, isM2, gpuInterfaceType, tdp);
    }
}