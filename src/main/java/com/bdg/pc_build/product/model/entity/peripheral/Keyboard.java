package com.bdg.pc_build.product.model.entity.peripheral;

import com.bdg.pc_build.product.model.dto.peripheral.KeyboardDTO;
import com.bdg.pc_build.product.model.entity.Product;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Objects;

@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "keyboard")
public class Keyboard extends Product {

    @Column(name = "keyboard_class", nullable = false, updatable = false)
    String keyboardClass;

    @Column(name = "cable_length", nullable = false, updatable = false)
    Double cableLength;

    @Column(name = "dimension", nullable = false, updatable = false)
    String dimension;

    @Column(name = "weight", nullable = false, updatable = false)
    Double weight;

    public Keyboard(final KeyboardDTO dto) {
        super(dto.getName(), dto.getPrice(), dto.getPurchasedPrice(), dto.getCount());
        this.keyboardClass = dto.getKeyboardClass();
        this.cableLength = dto.getCableLength();
        this.dimension = dto.getDimension();
        this.weight = dto.getWeight();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Keyboard keyboard = (Keyboard) o;
        return Objects.equals(keyboardClass, keyboard.keyboardClass)
                && Objects.equals(cableLength, keyboard.cableLength)
                && Objects.equals(dimension, keyboard.dimension)
                && Objects.equals(weight, keyboard.weight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(keyboardClass, cableLength, dimension, weight);
    }
}
