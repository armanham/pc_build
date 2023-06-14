package com.bdg.pc_build.product.model.entity.main_component;

import com.bdg.pc_build.product.model.dto.main_component.RAMDTO;
import com.bdg.pc_build.product.model.entity.Product;
import javax.persistence.*;

import com.bdg.pc_build.product.model.enumerations.DDRType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Objects;


/**
 * @author Arman Hakhverdyan
 * <p>
 * Entity for RAM
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "ram")
public class RAM extends Product {

    @Column(name = "speed", nullable = false, updatable = false)
    DDRType ddrType;

    @Column(name = "count_of_ram", nullable = false, updatable = false)
    Integer countOfRam;

    @Column(name = "gb_of_ram", nullable = false, updatable = false)
    Double gbOfRam;

    @Column(name = "tdp", nullable = false, updatable = false)
    Integer tdp;

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