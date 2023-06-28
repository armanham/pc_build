package com.bdg.pc_build.product.model.entity.main_component;

import com.bdg.pc_build.order.entity.Order;
import com.bdg.pc_build.product.enumerations.DDRType;
import com.bdg.pc_build.product.model.dto.main_component.RAMDTO;
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
@Table(name = "ram", schema = "product")
public class RAM extends Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ram_seq")
    @SequenceGenerator(name = "ram_seq", sequenceName = "ram_sequence", schema = "product",
            initialValue = InitialAndFinalIdValues.INITIAL_ID_VALUE_RAM, allocationSize = 1
    )
    @Column(name = "id")
    private Long id;

    @Column(name = "ddr_type", nullable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private DDRType ddrType;

    @Column(name = "count_of_ram", nullable = false, updatable = false)
    private Integer countOfRam;

    @Column(name = "gb_of_ram", nullable = false, updatable = false)
    private Integer gbOfRam;

    @Column(name = "tdp", nullable = false, updatable = false)
    private Integer tdp;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "rams")
    List<Order> orders;

    public RAM(final RAMDTO dto) {
        super(dto.getName(), dto.getPrice(), dto.getPurchasedPrice(), dto.getCount());
        this.ddrType = dto.getDdrType();
        this.countOfRam = dto.getCountOfRam();
        this.gbOfRam = dto.getGbOfRam();
        this.tdp = dto.getTdp();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RAM ram = (RAM) o;
        return Objects.equals(ddrType, ram.ddrType)
                && Objects.equals(countOfRam, ram.countOfRam)
                && Objects.equals(gbOfRam, ram.gbOfRam)
                && Objects.equals(tdp, ram.tdp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ddrType, countOfRam, gbOfRam, tdp);
    }
}