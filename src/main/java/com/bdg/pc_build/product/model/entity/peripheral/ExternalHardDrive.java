package com.bdg.pc_build.product.model.entity.peripheral;

import com.bdg.pc_build.product.model.InitialAndMaxValues;
import com.bdg.pc_build.product.model.dto.peripheral.ExternalHardDriveDTO;
import com.bdg.pc_build.product.model.entity.Product;
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
@Table(name = "external_hard_drive")
public class ExternalHardDrive extends Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "entity_seq")
    @SequenceGenerator(name = "entity_seq", sequenceName = "external_hard_drive_sequence", initialValue = InitialAndMaxValues.INITIAL_ID_VALUE_EXTERNAL_HARD_DRIVE)
    @Column(name = "id")
    private Long id;

    @Column(name = "capacity", nullable = false, updatable = false)
    Integer capacity;

    @Column(name = "tdp", nullable = false, updatable = false)
    Integer tdp;

    public ExternalHardDrive(final ExternalHardDriveDTO dto) {
        super(dto.getName(), dto.getPrice(), dto.getPurchasedPrice(), dto.getCount());
        this.capacity = dto.getCapacity();
        this.tdp = dto.getTdp();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExternalHardDrive that = (ExternalHardDrive) o;
        return Objects.equals(capacity, that.capacity)
                && Objects.equals(tdp, that.tdp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(capacity, tdp);
    }
}