package com.bdg.pc_build.product.model.entity.peripheral;

import com.bdg.pc_build.product.model.dto.peripheral.ExternalHardDriveDTO;
import com.bdg.pc_build.product.model.entity.Product;
import javax.persistence.*;
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