package com.bdg.pc_build.product.model.dto.main_component;


import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.entity.main_component.RAM;
import com.bdg.pc_build.product.model.request.creation.ProductRequest;
import lombok.*;
import lombok.experimental.FieldDefaults;

/**
 * @author Arman Hakhverdyan
 * <p>
 * An Immutable DataTransferObject of RAM for service layer.
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
@Setter
public class RAMDTO extends ProductDTO {

    Integer speed;
    Integer countOfRam;
    Double gbOfRam;
    Integer tdp;

    @Builder
    public RAMDTO(
            final String name,
            final Double price,
            final Double purchasedPrice,
            final Integer count,
            final Integer speed,
            final Integer countOfRam,
            final Double gbOfRam,
            final Integer tdp
    ) {
        super(name, price, purchasedPrice, count);
        this.speed = speed;
        this.countOfRam = countOfRam;
        this.gbOfRam = gbOfRam;
        this.tdp = tdp;
    }

    public static RAMDTO initDTOFromEntity(final RAM entity) {
        return RAMDTO.builder()
                .name(entity.getName())
                .price(entity.getPrice())
                .purchasedPrice(entity.getPurchasedPrice())
                .count(entity.getCount())
                .speed(entity.getSpeed())
                .countOfRam(entity.getCountOfRAM())
                .gbOfRam(entity.getGbOfRAM())
                .tdp(entity.getTdp())
                .build();
    }

    public static RAMDTO initDTOFromRequest(final ProductRequest request) {
        return RAMDTO.builder()
                .name(request.name())
                .price(Double.valueOf(request.price()))
                .purchasedPrice(Double.valueOf(request.purchasedPrice()))
                .count(Integer.valueOf(request.count()))
                .speed(Integer.valueOf(request.speed()))
                .countOfRam(Integer.valueOf(request.countOfRAM()))
                .gbOfRam(Double.valueOf(request.GBOfRAM()))
                .tdp(Integer.valueOf(request.tdp()))
                .build();
    }
}