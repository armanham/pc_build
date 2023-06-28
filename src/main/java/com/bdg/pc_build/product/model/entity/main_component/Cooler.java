package com.bdg.pc_build.product.model.entity.main_component;

import com.bdg.pc_build.order.model.entity.Order;
import com.bdg.pc_build.product.model.dto.main_component.CoolerDTO;
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
@Table(name = "cooler", schema = "product")
public class Cooler extends Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cooler_seq")
    @SequenceGenerator(
            name = "cooler_seq", sequenceName = "cooler_sequence", schema = "product",
            initialValue = InitialAndFinalIdValues.INITIAL_ID_VALUE_COOLER, allocationSize = 1
    )
    @Column(name = "id")
    private Long id;

    @Column(name = "tdp", nullable = false, updatable = false)
    private Integer tdp;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "coolers")
    private List<Order> orders;

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