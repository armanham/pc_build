package com.bdg.pc_build.product.model.dto.main_component;

import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.entity.main_component.Motherboard;
import com.bdg.pc_build.product.model.enumerations.ATXType;
import com.bdg.pc_build.product.model.enumerations.DDRType;
import com.bdg.pc_build.product.model.enumerations.GPUInterfaceType;
import com.bdg.pc_build.product.model.enumerations.SocketType;
import com.bdg.pc_build.product.model.request.creation.main_component.MotherboardCreationRequest;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Getter
public class MotherboardDTO extends ProductDTO {

    SocketType socketType;
    ATXType atxType;
    Integer memoryMax;
    Integer memorySlots;
    DDRType ddrType;
    Boolean isM2;
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
            final DDRType ddrType,
            final Boolean isM2,
            final GPUInterfaceType gpuInterfaceType,
            final Integer tdp
    ) {
        super(name, price, purchasedPrice, count);
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
        return MotherboardDTO.builder()
                .name(request.getName())
                .price(Double.valueOf(request.getPrice()))
                .purchasedPrice(Double.valueOf(request.getPurchasedPrice()))
                .count(Integer.valueOf(request.getCount()))
                .socketType(SocketType.valueOf(request.getSocketType().trim().toUpperCase()))
                .atxtype(ATXType.valueOf(request.getAtxType().trim().toUpperCase()))
                .memoryMax(Integer.valueOf(request.getMemoryMax()))
                .memorySlots(Integer.valueOf(request.getMemorySlots()))
                .ddrType(DDRType.valueOf(request.getDdrType().trim().toUpperCase()))
                .isM2(Boolean.valueOf(request.getIsM2().trim()))
                .gpuInterfaceType(GPUInterfaceType.valueOf(request.getGpuInterfaceType().trim().toUpperCase()))
                .tdp(Integer.valueOf(request.getTdp()))
                .build();
    }
}