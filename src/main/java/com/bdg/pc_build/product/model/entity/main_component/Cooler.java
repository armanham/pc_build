package com.bdg.pc_build.product.model.entity.main_component;

import com.bdg.pc_build.product.model.InitialAndFinalIdValues;
import com.bdg.pc_build.product.model.dto.main_component.CoolerDTO;
import com.bdg.pc_build.product.model.entity.Product;
import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "cooler", schema = "product")
public class Cooler extends Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "entity_seq")
    @SequenceGenerator(name = "entity_seq", sequenceName = "cooler_sequence", initialValue = InitialAndFinalIdValues.INITIAL_ID_VALUE_COOLER)
    @Column(name = "id")
    private Long id;

    @Column(name = "tdp", nullable = false, updatable = false)
    private Integer tdp;

    public Cooler(final CoolerDTO dto) {
        super(dto.getName(), dto.getPrice(), dto.getPurchasedPrice(), dto.getCount());
        this.tdp = dto.getTdp();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cooler cooler = (Cooler) o;
        return Objects.equals(tdp, cooler.tdp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tdp);
    }
}