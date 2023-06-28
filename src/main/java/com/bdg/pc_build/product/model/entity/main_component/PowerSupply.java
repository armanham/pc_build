package com.bdg.pc_build.product.model.entity.main_component;

import com.bdg.pc_build.computer_builder.model.entity.Computer;
import com.bdg.pc_build.order.model.entity.Order;
import com.bdg.pc_build.product.enumerations.EfficiencyRating;
import com.bdg.pc_build.product.enumerations.ModularType;
import com.bdg.pc_build.product.model.dto.main_component.PowerSupplyDTO;
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
@Table(name = "power_supply", schema = "product")
public class PowerSupply extends Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "power_supply_seq")
    @SequenceGenerator(
            name = "power_supply_seq", sequenceName = "power_supply_sequence", schema = "product",
            initialValue = InitialAndFinalIdValues.INITIAL_ID_VALUE_POWER_SUPPLY, allocationSize = 1
    )
    @Column(name = "id")
    private Long id;

    @Column(name = "efficiency_rating", nullable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private EfficiencyRating efficiencyRating;

    @Column(name = "wattage", nullable = false, updatable = false)
    private Integer wattage;

    @Column(name = "modular_type", nullable = false, updatable = false)
    private ModularType modularType;

    @Column(name = "tdp", nullable = false, updatable = false)
    private Integer tdp;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "powerSupplies")
    private List<Order> orders;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "powerSupply")
    private List<Computer> computers;

    public PowerSupply(final PowerSupplyDTO dto) {
        super(dto.getName(), dto.getPrice(), dto.getPurchasedPrice(), dto.getCount());
        this.efficiencyRating = dto.getEfficiencyRating();
        this.wattage = dto.getWattage();
        this.modularType = dto.getModularType();
        this.tdp = dto.getTdp();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PowerSupply that = (PowerSupply) o;
        return Objects.equals(efficiencyRating, that.efficiencyRating)
                && Objects.equals(wattage, that.wattage)
                && Objects.equals(modularType, that.modularType)
                && Objects.equals(tdp, that.tdp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(efficiencyRating, wattage, modularType, tdp);
    }
}