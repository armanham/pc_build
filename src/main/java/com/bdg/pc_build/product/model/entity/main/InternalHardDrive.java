package com.bdg.pc_build.product.model.entity.main;

import com.bdg.pc_build.pc_builder.model.entity.Computer;
import com.bdg.pc_build.order.model.entity.Order;
import com.bdg.pc_build.product.enumerations.InternalHardDriveInterfaceType;
import com.bdg.pc_build.product.model.dto.main.InternalHardDriveDTO;
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
@Table(name = "internal_hard_drive", schema = "product")
public class InternalHardDrive extends Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "internal_hard_drive_seq")
    @SequenceGenerator(
            name = "internal_hard_drive_seq", sequenceName = "internal_hard_drive_sequence", schema = "product",
            initialValue = InitialAndFinalIdValues.INITIAL_ID_VALUE_INTERNAL_HARD_DRIVE, allocationSize = 1
    )
    @Column(name = "id")
    private Long id;

    @Column(name = "internal_hard_drive_interface_type", nullable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private InternalHardDriveInterfaceType internalHardDriveInterfaceType;

    @Column(name = "capacity", nullable = false, updatable = false)
    private Integer capacity;

    @Column(name = "tdp", nullable = false, updatable = false)
    private Integer tdp;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "internalHardDrives")
    private List<Order> orders;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "internalHardDrives")
    private List<Computer> computers;

    public InternalHardDrive(final InternalHardDriveDTO dto) {
        super(dto.getName(), dto.getPrice(), dto.getPurchasedPrice(), dto.getCount());
        this.internalHardDriveInterfaceType = dto.getInternalHardDriveInterfaceType();
        this.capacity = dto.getCapacity();
        this.tdp = dto.getTdp();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InternalHardDrive that = (InternalHardDrive) o;
        return Objects.equals(internalHardDriveInterfaceType, that.internalHardDriveInterfaceType)
                && Objects.equals(capacity, that.capacity)
                && Objects.equals(tdp, that.tdp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(internalHardDriveInterfaceType, capacity, tdp);
    }
}