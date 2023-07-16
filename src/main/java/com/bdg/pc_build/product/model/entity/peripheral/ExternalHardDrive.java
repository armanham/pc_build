package com.bdg.pc_build.product.model.entity.peripheral;

import com.bdg.pc_build.pc_builder.model.entity.Computer;
import com.bdg.pc_build.order.model.entity.Order;
import com.bdg.pc_build.product.model.dto.peripheral.ExternalHardDriveDTO;
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
@Table(name = "external_hard_drive", schema = "product")
public class ExternalHardDrive extends Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "external_hard_drive_seq")
    @SequenceGenerator(name = "external_hard_drive_seq", sequenceName = "external_hard_drive_sequence", schema = "product",
            initialValue = InitialAndFinalIdValues.INITIAL_ID_VALUE_EXTERNAL_HARD_DRIVE, allocationSize = 1
    )
    @Column(name = "id")
    private Long id;

    @Column(name = "capacity", nullable = false, updatable = false)
    private Integer capacity;

    @Column(name = "tdp", nullable = false, updatable = false)
    private Integer tdp;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "externalHardDrives")
    private List<Order> orders;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "externalHardDrives")
    private List<Computer> computers;

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