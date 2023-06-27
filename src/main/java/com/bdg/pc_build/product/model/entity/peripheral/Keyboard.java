package com.bdg.pc_build.product.model.entity.peripheral;

import com.bdg.pc_build.product.model.InitialAndFinalIdValues;
import com.bdg.pc_build.product.model.dto.peripheral.KeyboardDTO;
import com.bdg.pc_build.product.model.entity.Product;
import com.bdg.pc_build.product.enumerations.ConnectivityType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "keyboard", schema = "product")
public class Keyboard extends Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "entity_seq")
    @SequenceGenerator(name = "entity_seq", sequenceName = "keyboard_sequence", initialValue = InitialAndFinalIdValues.INITIAL_ID_VALUE_KEYBOARD)
    @Column(name = "id")
    private Long id;

    @Column(name = "connectivity_type", nullable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private ConnectivityType connectivityType;

    @Column(name = "cable_length", nullable = false, updatable = false)
    private Double cableLength;

    @Column(name = "dimension", nullable = false, updatable = false)
    private String dimension;

    @Column(name = "weight", nullable = false, updatable = false)
    private Double weight;

    public Keyboard(final KeyboardDTO dto) {
        super(dto.getName(), dto.getPrice(), dto.getPurchasedPrice(), dto.getCount());
        this.connectivityType = dto.getConnectivityType();
        this.cableLength = dto.getCableLength();
        this.dimension = dto.getDimension();
        this.weight = dto.getWeight();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Keyboard keyboard = (Keyboard) o;
        return Objects.equals(connectivityType, keyboard.connectivityType)
                && Objects.equals(cableLength, keyboard.cableLength)
                && Objects.equals(dimension, keyboard.dimension)
                && Objects.equals(weight, keyboard.weight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(connectivityType, cableLength, dimension, weight);
    }
    
}