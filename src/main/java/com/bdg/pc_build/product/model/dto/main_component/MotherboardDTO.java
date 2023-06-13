package com.bdg.pc_build.product.model.dto.main_component;

import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.entity.main_component.Motherboard;
import com.bdg.pc_build.product.model.enumerations.ATXType;
import com.bdg.pc_build.product.model.enumerations.DDRType;
import com.bdg.pc_build.product.model.enumerations.GPUInterfaceType;
import com.bdg.pc_build.product.model.enumerations.SocketType;
import com.bdg.pc_build.product.model.request.creation.main_component.MotherboardCreationRequest;
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

    public static MotherboardDTO initDTOFromRequest(final MotherboardCreationRequest request) {
        return MotherboardDTO.builder()
                .name(request.getName())
                .price(Double.valueOf(request.getPrice()))
                .purchasedPrice(Double.valueOf(request.getPurchasedPrice()))
                .count(Integer.valueOf(request.getCount()))
                .socketType(SocketType.valueOf(request.getSocketType()))
                .atxtype(ATXType.valueOf(request.getAtxType()))
                .memoryMax(Integer.valueOf(request.getMemoryMax()))
                .memorySlots(Integer.valueOf(request.getMemorySlots()))
                .memoryType(DDRType.valueOf(request.getMemoryType()))
                .internalConnections(request.getInternalConnections())
                .gpuInterfaceType(GPUInterfaceType.valueOf(request.getGpuInterfaceType()))
                .tdp(Integer.valueOf(request.getTdp()))
                .build();
    }
}