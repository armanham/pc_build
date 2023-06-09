package com.bdg.pc_build.product.model.dto.main_component;


import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.dto.display.MonitorDTO;
import com.bdg.pc_build.product.model.entity.display.Monitor;
import com.bdg.pc_build.product.model.entity.main_component.RAM;
import com.bdg.pc_build.product.model.request.ProductRequest;
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
    Integer countOfRAM;
    Double GBOfRAM;
    Integer tdp;

    @Builder
    public RAMDTO(
            final String name,
            final Double price,
            final Double purchasedPrice,
            final Integer count,
            final Integer speed,
            final Integer countOfRAM,
            final Double GBOfRAM,
            final Integer tdp
    ) {
        super(name, price, purchasedPrice, count);
        this.speed = speed;
        this.countOfRAM = countOfRAM;
        this.GBOfRAM = GBOfRAM;
        this.tdp = tdp;
    }

    public static RAMDTO initDTOFromEntity(final RAM entity) {
        return RAMDTO.builder()
                .name(entity.getName())
                .price(entity.getPrice())
                .purchasedPrice(entity.getPurchasedPrice())
                .count(entity.getCount())
                .speed(entity.getSpeed())
                .countOfRAM(entity.getCountOfRAM())
                .GBOfRAM(entity.getGbOfRAM())
                .tdp(entity.getTdp())
                .build();
    }
    public static RAMDTO initDTOFromRequest(final ProductRequest request){
        return RAMDTO.builder()
                .name(request.name())
                .price(request.price())
                .purchasedPrice(request.purchasedPrice())
                .count(request.count())
                .speed(request.speed())
                .countOfRAM(request.countOfRAM())
                .GBOfRAM(request.GBOfRAM())
                .tdp(request.tdp())
                .build();
    }
}
