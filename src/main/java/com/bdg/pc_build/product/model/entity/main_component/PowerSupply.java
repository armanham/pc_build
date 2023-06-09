package com.bdg.pc_build.product.model.entity.main_component;

import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.dto.main_component.PowerSupplyDTO;
import com.bdg.pc_build.product.model.entity.Product;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Objects;

/**
 * @Author Arman Hakhverdyan
 * <p>
 * Entity for PowerSupply
 */
@Entity
@Table(name = "power_supply")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class PowerSupply extends Product {

    @Column(name = "efficiency_rating", nullable = false, updatable = false)
    String efficiencyRating;

    @Column(name = "wattage", nullable = false, updatable = false)
    Integer wattage;

    @Column(name = "modular", nullable = false, updatable = false)
    Boolean modular;

    @Column(name = "tdp", nullable = false, updatable = false)
    Integer tdp;

    public PowerSupply(PowerSupplyDTO dto) {
        super(dto.getName(), dto.getPrice(), dto.getPurchasedPrice(), dto.getCount());
        this.efficiencyRating = dto.getEfficiencyRating();
        this.wattage = dto.getWattage();
        this.modular = dto.getModular();
        this.tdp = dto.getTdp();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PowerSupply that = (PowerSupply) o;
        return Objects.equals(efficiencyRating, that.efficiencyRating)
                && Objects.equals(wattage, that.wattage)
                && Objects.equals(modular, that.modular)
                && Objects.equals(tdp, that.tdp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(efficiencyRating, wattage, modular, tdp);
    }
}