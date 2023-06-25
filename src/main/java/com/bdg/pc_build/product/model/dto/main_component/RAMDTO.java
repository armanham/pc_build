package com.bdg.pc_build.product.model.dto.main_component;


import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.entity.main_component.RAM;
import com.bdg.pc_build.product.enumerations.DDRType;
import com.bdg.pc_build.product.model.request.creation.main_component.RAMCreationRequest;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Getter
public class RAMDTO extends ProductDTO {

    DDRType ddrType;
    Integer countOfRam;
    Double gbOfRam;
    Integer tdp;

    @Builder
    public RAMDTO(
            final Long id,
            final String name,
            final Double price,
            final Double purchasedPrice,
            final Integer count,
            final DDRType ddrType,
            final Integer countOfRam,
            final Double gbOfRam,
            final Integer tdp
    ) {
        super(id, name, price, purchasedPrice, count);
        this.ddrType = ddrType;
        this.countOfRam = countOfRam;
        this.gbOfRam = gbOfRam;
        this.tdp = tdp;
    }

    public static RAMDTO initDTOFromEntity(final RAM entity) {
        return RAMDTO.builder()
                .id(entity.getId())
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
        if ((Integer.parseInt(request.getGbOfRam()) & Integer.parseInt(request.getGbOfRam()) - 1) != 0) {
            throw new IllegalArgumentException("'memoryMax' must be power of two");
        }
        return RAMDTO.builder()
                .name(request.getName())
                .price(Double.valueOf(request.getPrice()))
                .purchasedPrice(Double.valueOf(request.getPurchasedPrice()))
                .count(Integer.valueOf(request.getCount()))
                .ddrType(DDRType.valueOf(request.getDdrType().trim().toUpperCase()))
                .countOfRam(Integer.valueOf(request.getCountOfRam()))
                .gbOfRam(Double.valueOf(request.getGbOfRam()))
                .tdp(Integer.valueOf(request.getTdp()))
                .build();
    }
}