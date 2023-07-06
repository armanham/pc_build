package com.bdg.pc_build.product.model.dto.main;

import com.bdg.pc_build.product.enumerations.ATXType;
import com.bdg.pc_build.product.enumerations.DDRType;
import com.bdg.pc_build.product.enumerations.GPUInterfaceType;
import com.bdg.pc_build.product.enumerations.SocketType;
import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.entity.main.Motherboard;
import com.bdg.pc_build.product.model.request.creation.main.MotherboardCreationRequest;
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

    public MotherboardDTO(final Motherboard entity) {
        super(entity.getId(), entity.getName(), entity.getPrice(), entity.getPurchasedPrice(), entity.getCount());
        this.socketType = entity.getSocketType();
        this.atxType = entity.getAtxType();
        this.memoryMax = entity.getMemoryMax();
        this.memorySlots = entity.getMemorySlots();
        this.ddrType = entity.getDdrType();
        this.isM2 = entity.getIsM2();
        this.gpuInterfaceType = entity.getGpuInterfaceType();
        this.tdp = entity.getTdp();
    }

    public MotherboardDTO(final MotherboardCreationRequest request) {
        super(request.getName(), request.getPrice(), request.getPurchasedPrice(), request.getCount());

        if ((request.getMemoryMax() & request.getMemoryMax() - 1) != 0) {
            throw new IllegalArgumentException("Memory max of Motherboard must be a power of two: ");
        }
        if (request.getMemorySlots() % 2 != 0) {
            throw new IllegalArgumentException("Memory slots of Motherboard must be an even number: ");
        }
        this.socketType = SocketType.valueOf(request.getSocketType().trim().toUpperCase());
        this.atxType = ATXType.valueOf(request.getAtxType().trim().toUpperCase());
        this.memoryMax = request.getMemoryMax();
        this.memorySlots = request.getMemorySlots();
        this.ddrType = DDRType.valueOf(request.getDdrType().trim().toUpperCase());
        this.isM2 = Boolean.valueOf(request.getIsM2().trim());
        this.gpuInterfaceType = GPUInterfaceType.valueOf(request.getGpuInterfaceType().trim().toUpperCase());
        this.tdp = request.getTdp();
    }

    @Override
    public String toString() {
        return "MotherboardDTO{" +
                "socketType=" + socketType +
                ", atxType=" + atxType +
                ", memoryMax=" + memoryMax +
                ", memorySlots=" + memorySlots +
                ", ddrType=" + ddrType +
                ", isM2=" + isM2 +
                ", gpuInterfaceType=" + gpuInterfaceType +
                ", tdp=" + tdp +
                '}';
    }
}