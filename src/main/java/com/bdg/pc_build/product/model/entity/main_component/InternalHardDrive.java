package com.bdg.pc_build.product.model.entity.main_component;

import com.bdg.pc_build.product.model.dto.main_component.InternalHardDriveDTO;
import com.bdg.pc_build.product.model.entity.Product;
import com.bdg.pc_build.product.enumerations.InternalHardDriveInterfaceType;
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
@Table(name = "internal_hard_drive")
public class InternalHardDrive extends Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "entity_seq")
    @SequenceGenerator(name = "entity_seq", sequenceName = "internal_hard_drive_sequence", initialValue = 1501)
    @Column(name = "id")
    private Long id;

    @Column(name = "internal_hard_drive_interface_type", nullable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    InternalHardDriveInterfaceType internalHardDriveInterfaceType;

    @Column(name = "capacity", nullable = false, updatable = false)
    Integer capacity;

    @Column(name = "tdp", nullable = false, updatable = false)
    Integer tdp;

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