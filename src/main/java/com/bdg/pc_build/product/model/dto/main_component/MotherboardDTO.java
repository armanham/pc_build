package com.bdg.pc_build.product.model.dto.main_component;

import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.dto.display.MonitorDTO;
import com.bdg.pc_build.product.model.entity.display.Monitor;
import com.bdg.pc_build.product.model.entity.main_component.Motherboard;
import com.bdg.pc_build.product.model.request.ProductRequest;
import lombok.*;
import lombok.experimental.FieldDefaults;

/**
  * @author Arman Hakhverdyan
 * <p>
 * An Immutable DataTransferObject of Motherboard for service layer.
 */
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
@Getter
@Setter
public class MotherboardDTO extends ProductDTO {

    String socketCpu;
    String formFactor;
    Integer memoryMax;
    Integer memorySlots;
    String memoryType;
    Integer tdp;

    @Builder
    public MotherboardDTO(
            final String name,
            final Double price,
            final Double purchasedPrice,
            final Integer count,
            final String socketCpu,
            final String formFactor,
            final Integer memoryMax,
            final Integer memorySlots,
            final String memoryType,
            final Integer tdp
    ) {
        super(name, price, purchasedPrice, count);
        this.socketCpu = socketCpu;
        this.formFactor = formFactor;
        this.memoryMax = memoryMax;
        this.memorySlots = memorySlots;
        this.memoryType = memoryType;
        this.tdp = tdp;
    }

    public static MotherboardDTO initDTOFromEntity(final Motherboard entity) {
        return MotherboardDTO.builder()
                .name(entity.getName())
                .price(entity.getPrice())
                .purchasedPrice(entity.getPurchasedPrice())
                .count(entity.getCount())
                .socketCpu(entity.getSocketCpu())
                .formFactor(entity.getFormFactor())
                .memoryMax(entity.getMemoryMax())
                .memorySlots(entity.getMemorySlots())
                .memoryType(entity.getMemoryType())
                .tdp(entity.getTdp())
                .build();
    }

    public static MotherboardDTO initDTOFromRequest(final ProductRequest request){
        return MotherboardDTO.builder()
                .name(request.name())
                .price(request.price())
                .purchasedPrice(request.purchasedPrice())
                .count(request.count())
                .socketCpu(request.socketCpu())
                .formFactor(request.formFactor())
                .memoryMax(request.memoryMax())
                .memorySlots(request.memorySlots())
                .memoryType(request.memoryType())
                .tdp(request.tdp())
                .build();
    }

}