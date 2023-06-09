package com.bdg.pc_build.product.model.entity.peripheral;

import com.bdg.pc_build.product.model.dto.peripheral.MouseDTO;
import com.bdg.pc_build.product.model.entity.Product;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Objects;

@Entity
@Table(name = "mouse")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Mouse extends Product {

    @Column(name = "type", nullable = false, updatable = false)
    String type;

    @Column(name = "max_resolution", nullable = false, updatable = false)
    Integer maxResolution;

    @Column(name = "cable_length", nullable = false, updatable = false)
    Double cableLength;

    @Column(name = "weight", nullable = false, updatable = false)
    Double weight;

    public Mouse(final MouseDTO dto) {
        super(dto.getName(), dto.getPrice(), dto.getPurchasedPrice(), dto.getCount());
        this.type = dto.getType();
        this.maxResolution = dto.getMaxResolution();
        this.cableLength = dto.getCableLength();
        this.weight = dto.getWeight();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mouse mouse = (Mouse) o;
        return Objects.equals(type, mouse.type)
                && Objects.equals(maxResolution, mouse.maxResolution)
                && Objects.equals(cableLength, mouse.cableLength)
                && Objects.equals(weight, mouse.weight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, maxResolution, cableLength, weight);
    }
}
