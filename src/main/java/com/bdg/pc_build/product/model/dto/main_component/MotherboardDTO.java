package com.bdg.pc_build.product.model.dto.main_component;

import com.bdg.pc_build.product.enumerations.ATXType;
import com.bdg.pc_build.product.enumerations.DDRType;
import com.bdg.pc_build.product.enumerations.GPUInterfaceType;
import com.bdg.pc_build.product.enumerations.SocketType;
import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.entity.main_component.Motherboard;
import com.bdg.pc_build.product.model.request.creation.main_component.MotherboardCreationRequest;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MotherboardDTO extends ProductDTO {

    private final SocketType socketType;
    private final ATXType atxType;
    private final Integer memoryMax;
    private final Integer memorySlots;
    private final DDRType ddrType;
    private final Boolean isM2;
    private final GPUInterfaceType gpuInterfaceType;
    private final Integer tdp;

    @Builder
    public MotherboardDTO(
            final Long id,
            final String name,
            final Double price,
            final Double purchasedPrice,
            final Integer count,
            final SocketType socketType,
            final ATXType atxtype,
            final Integer memoryMax,
            final Integer memorySlots,
            final DDRType ddrType,
            final Boolean isM2,
            final GPUInterfaceType gpuInterfaceType,
            final Integer tdp
    ) {
        super(id, name, price, purchasedPrice, count);
        this.socketType = socketType;
        this.atxType = atxtype;
        this.memoryMax = memoryMax;
        this.memorySlots = memorySlots;
        this.ddrType = ddrType;
        this.isM2 = isM2;
        this.gpuInterfaceType = gpuInterfaceType;
        this.tdp = tdp;
    }

    public static MotherboardDTO initDTOFromEntity(final Motherboard entity) {
        return MotherboardDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .price(entity.getPrice())
                .purchasedPrice(entity.getPurchasedPrice())
                .count(entity.getCount())
                .socketType(entity.getSocketType())
                .atxtype(entity.getAtxType())
                .memoryMax(entity.getMemoryMax())
                .memorySlots(entity.getMemorySlots())
                .ddrType(entity.getDdrType())
                .isM2(entity.getIsM2())
                .gpuInterfaceType(entity.getGpuInterfaceType())
                .tdp(entity.getTdp())
                .build();
    }

    public static MotherboardDTO initDTOFromRequest(final MotherboardCreationRequest request) {
        if ((request.getMemoryMax() & request.getMemoryMax() - 1) != 0) {
            throw new IllegalArgumentException("'memoryMax' must be power of two");
        }
        if (request.getMemorySlots() % 2 != 0) {
            throw new IllegalArgumentException("'memorySlots' must be even number");
        }
        return MotherboardDTO.builder()
                .name(request.getName().trim())
                .price(request.getPrice())
                .purchasedPrice(request.getPurchasedPrice())
                .count(request.getCount())
                .socketType(SocketType.valueOf(request.getSocketType().trim().toUpperCase()))
                .atxtype(ATXType.valueOf(request.getAtxType().trim().toUpperCase()))
                .memoryMax(request.getMemoryMax())
                .memorySlots(request.getMemorySlots())
                .ddrType(DDRType.valueOf(request.getDdrType().trim().toUpperCase()))
                .isM2(Boolean.valueOf(request.getIsM2().trim()))
                .gpuInterfaceType(GPUInterfaceType.valueOf(request.getGpuInterfaceType().trim().toUpperCase()))
                .tdp(request.getTdp())
                .build();
    }
}