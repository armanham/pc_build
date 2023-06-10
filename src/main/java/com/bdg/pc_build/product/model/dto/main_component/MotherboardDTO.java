package com.bdg.pc_build.product.model.dto.main_component;

import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.entity.main_component.Motherboard;
import com.bdg.pc_build.product.model.enumerations.ATXType;
import com.bdg.pc_build.product.model.enumerations.DDRType;
import com.bdg.pc_build.product.model.enumerations.GPUInterface;
import com.bdg.pc_build.product.model.enumerations.SocketType;
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

    SocketType socketCpu;
    ATXType atxType;
    Integer memoryMax;
    Integer memorySlots;
    DDRType memoryType;
    String internalConnections;
    GPUInterface gpuInterface;
    Integer tdp;

    @Builder
    public MotherboardDTO(
            final String name,
            final Double price,
            final Double purchasedPrice,
            final Integer count,
            final SocketType socketCpu,
            final ATXType atxtype,
            final Integer memoryMax,
            final Integer memorySlots,
            final DDRType memoryType,
            final String internalConnections,
            final GPUInterface gpuInterface,
            final Integer tdp
    ) {
        super(name, price, purchasedPrice, count);
        this.socketCpu = socketCpu;
        this.atxType = atxtype;
        this.memoryMax = memoryMax;
        this.memorySlots = memorySlots;
        this.memoryType = memoryType;
        this.internalConnections = internalConnections;
        this.gpuInterface = gpuInterface;
        this.tdp = tdp;
    }

    public static MotherboardDTO initDTOFromEntity(final Motherboard entity) {
        return MotherboardDTO.builder()
                .name(entity.getName())
                .price(entity.getPrice())
                .purchasedPrice(entity.getPurchasedPrice())
                .count(entity.getCount())
                .socketCpu(entity.getSocketTypeCpu())
                .atxtype(entity.getAtxType())
                .memoryMax(entity.getMemoryMax())
                .memorySlots(entity.getMemorySlots())
                .memoryType(entity.getMemoryType())
                .internalConnections(String.valueOf(entity.getInternalConnections()))
                .gpuInterface(entity.getGpuInterface())
                .tdp(entity.getTdp())
                .build();
    }

    public static MotherboardDTO initDTOFromRequest(final ProductRequest request) {
        return MotherboardDTO.builder()
                .name(request.name())
                .price(request.price())
                .purchasedPrice(request.purchasedPrice())
                .count(request.count())
                .socketCpu(request.socketCpu())
                .atxtype(request.atxType())
                .memoryMax(request.memoryMax())
                .memorySlots(request.memorySlots())
                .memoryType(request.memoryType())
                .internalConnections(request.internalConnections())
                .gpuInterface(request.gpuInterface())
                .tdp(request.tdp())
                .build();
    }

}