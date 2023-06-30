package com.bdg.pc_build.product.model.dto.main_component;

import com.bdg.pc_build.product.enumerations.GPUInterfaceType;
import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.entity.main_component.GPU;
import com.bdg.pc_build.product.model.request.creation.main_component.GPUCreationRequest;
import lombok.Getter;

@Getter
public class GPUDTO extends ProductDTO {

    private final GPUInterfaceType gpuInterfaceType;
    private final Integer memory;
    private final Double coreClock;
    private final Double boostClock;
    private final Double length;
    private final Integer tdp;

    public GPUDTO(final GPU entity) {
        super(entity.getId(), entity.getName(), entity.getPrice(), entity.getPurchasedPrice(), entity.getCount());
        this.gpuInterfaceType = entity.getGpuInterfaceType();
        this.memory = entity.getMemory();
        this.coreClock = entity.getCoreClock();
        this.boostClock = entity.getBoostClock();
        this.length = entity.getLength();
        this.tdp = entity.getTdp();
    }

    public GPUDTO(final GPUCreationRequest request) {
        super(request.getName(), request.getPrice(), request.getPurchasedPrice(), request.getCount());

        if (request.getMemory() % 2 != 0) {
            throw new IllegalArgumentException("Memory of GPU must be an even number: ");
        }
        if (request.getCoreClock() >= request.getBoostClock()) {
            throw new IllegalArgumentException("Boost clock of GPU must be greater than core clock of GPU: ");
        }
        this.gpuInterfaceType = GPUInterfaceType.valueOf(request.getGpuInterfaceType().trim().toUpperCase());
        this.memory = request.getMemory();
        this.coreClock = request.getCoreClock();
        this.boostClock = request.getBoostClock();
        this.length = request.getLength();
        this.tdp = request.getTdp();
    }

    @Override
    public String toString() {
        return "GPUDTO{" +
                "gpuInterfaceType=" + gpuInterfaceType +
                ", memory=" + memory +
                ", coreClock=" + coreClock +
                ", boostClock=" + boostClock +
                ", length=" + length +
                ", tdp=" + tdp +
                '}';
    }
}