package com.bdg.pc_build.product.model.dto.main_component;

import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.entity.main_component.Motherboard;
import com.bdg.pc_build.product.model.enumerations.ATXType;
import com.bdg.pc_build.product.model.enumerations.DDRType;
import com.bdg.pc_build.product.model.enumerations.GPUInterfaceType;
import com.bdg.pc_build.product.model.enumerations.SocketType;
import com.bdg.pc_build.product.model.request.creation.ProductRequest;
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

    SocketType socketType;
    ATXType atxType;
    Integer memoryMax;
    Integer memorySlots;
    DDRType memoryType;
    String internalConnections;
    GPUInterfaceType gpuInterfaceType;
    Integer tdp;

    @Builder
    public MotherboardDTO(
            final String name,
            final Double price,
            final Double purchasedPrice,
            final Integer count,
            final SocketType socketType,
            final ATXType atxtype,
            final Integer memoryMax,
            final Integer memorySlots,
            final DDRType memoryType,
            final String internalConnections,
            final GPUInterfaceType gpuInterfaceType,
            final Integer tdp
    ) {
        super(name, price, purchasedPrice, count);
        this.socketType = socketType;
        this.atxType = atxtype;
        this.memoryMax = memoryMax;
        this.memorySlots = memorySlots;
        this.memoryType = memoryType;
        this.internalConnections = internalConnections;
        this.gpuInterfaceType = gpuInterfaceType;
        this.tdp = tdp;
    }

    public static MotherboardDTO initDTOFromEntity(final Motherboard entity) {
        return MotherboardDTO.builder()
                .name(entity.getName())
                .price(entity.getPrice())
                .purchasedPrice(entity.getPurchasedPrice())
                .count(entity.getCount())
                .socketType(entity.getSocketType())
                .atxtype(entity.getAtxType())
                .memoryMax(entity.getMemoryMax())
                .memorySlots(entity.getMemorySlots())
                .memoryType(entity.getMemoryType())
                .internalConnections(String.valueOf(entity.getInternalConnections()))
                .gpuInterfaceType(entity.getGpuInterfaceType())
                .tdp(entity.getTdp())
                .build();
    }

    public static MotherboardDTO initDTOFromRequest(final ProductRequest request) {
        return MotherboardDTO.builder()
                .name(request.name())
                .price(Double.valueOf(request.price()))
                .purchasedPrice(Double.valueOf(request.purchasedPrice()))
                .count(Integer.valueOf(request.count()))
                .socketType(SocketType.valueOf(request.socketType()))
                .atxtype(ATXType.valueOf(request.atxType()))
                .memoryMax(Integer.valueOf(request.memoryMax()))
                .memorySlots(Integer.valueOf(request.memorySlots()))
                .memoryType(DDRType.valueOf(request.memoryType()))
                .internalConnections(request.internalConnections())
                .gpuInterfaceType(GPUInterfaceType.valueOf(request.gpuInterfaceType()))
                .tdp(Integer.valueOf(request.tdp()))
                .build();
    }
}