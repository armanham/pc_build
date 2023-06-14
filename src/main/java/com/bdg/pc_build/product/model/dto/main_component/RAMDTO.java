package com.bdg.pc_build.product.model.dto.main_component;


import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.entity.main_component.RAM;
import com.bdg.pc_build.product.model.enumerations.DDRType;
import com.bdg.pc_build.product.model.request.creation.main_component.RAMCreationRequest;
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
public class RAMDTO extends ProductDTO {

    DDRType ddrType;
    Integer countOfRam;
    Double gbOfRam;
    Integer tdp;

    @Builder
    public RAMDTO(
            final String name,
            final Double price,
            final Double purchasedPrice,
            final Integer count,
            final DDRType ddrType,
            final Integer countOfRam,
            final Double gbOfRam,
            final Integer tdp
    ) {
        super(name, price, purchasedPrice, count);
        this.ddrType = ddrType;
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
                .ddrType(entity.getDdrType())
                .countOfRam(entity.getCountOfRam())
                .gbOfRam(entity.getGbOfRam())
                .tdp(entity.getTdp())
                .build();
    }

    public static RAMDTO initDTOFromRequest(final RAMCreationRequest request) {
        return RAMDTO.builder()
                .name(request.getName())
                .price(Double.valueOf(request.getPrice()))
                .purchasedPrice(Double.valueOf(request.getPurchasedPrice()))
                .count(Integer.valueOf(request.getCount()))
                .ddrType(DDRType.valueOf(request.getDdrType()))
                .countOfRam(Integer.valueOf(request.getCountOfRam()))
                .gbOfRam(Double.valueOf(request.getGbOfRam()))
                .tdp(Integer.valueOf(request.getTdp()))
                .build();
    }
}