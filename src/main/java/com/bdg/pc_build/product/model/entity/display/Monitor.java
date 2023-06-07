package com.bdg.pc_build.product.model.entity.display;

import com.bdg.pc_build.product.model.entity.Product;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;


@Entity
@Table(name = "monitor")
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Setter
@Getter
public class Monitor extends Product {
    @Column(name = "screen_type", nullable = false, updatable = false)
    Double screenSize;

    @Column(name = "refresh_rate", nullable = false, updatable = false)
    Integer refreshRate;

    @Column(name = "screen_type", nullable = false, updatable = false)
    String screenType;
}
