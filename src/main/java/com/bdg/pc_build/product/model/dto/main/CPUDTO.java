package com.bdg.pc_build.product.model.dto.main;

import com.bdg.pc_build.product.enumerations.SocketType;
import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.entity.main.CPU;
import com.bdg.pc_build.product.model.request.creation.main.CPUCreationRequest;
import lombok.Getter;

@Getter
public class CPUDTO extends ProductDTO {

    private final Integer coreCount;
    private final Double coreClock;
    private final Double boostClock;
    private final Integer tdp;
    private final String integratedGraphics;
    private final SocketType socketType;

    public CPUDTO(final CPU entity) {
        super(entity.getId(), entity.getName(), entity.getPrice(), entity.getPurchasedPrice(), entity.getCount());
        this.coreCount = entity.getCoreCount();
        this.coreClock = entity.getCoreClock();
        this.boostClock = entity.getBoostClock();
        this.tdp = entity.getTdp();
        this.integratedGraphics = entity.getIntegratedGraphics();
        this.socketType = entity.getSocketType();
    }


    public CPUDTO(final CPUCreationRequest request) {
        super(request.getName(), request.getPrice(), request.getPurchasedPrice(), request.getCount());

        if (request.getCoreCount() % 2 != 0) {
            throw new IllegalArgumentException("Core count of CPU must be an even number: ");
        }
        if (request.getCoreClock() >= request.getBoostClock()) {
            throw new IllegalArgumentException("Boost clock of CPU must be greater than core clock of CPU: ");
        }
        this.coreCount = request.getCoreCount();
        this.coreClock = request.getCoreClock();
        this.boostClock = request.getBoostClock();
        this.tdp = request.getTdp();
        this.integratedGraphics = request.getIntegratedGraphics().trim();
        this.socketType = SocketType.valueOf(request.getSocketType().trim().toUpperCase());
    }

    @Override
    public String toString() {
        return "CPUDTO{" +
                "coreCount=" + coreCount +
                ", coreClock=" + coreClock +
                ", boostClock=" + boostClock +
                ", tdp=" + tdp +
                ", integratedGraphics='" + integratedGraphics + '\'' +
                ", socketType=" + socketType +
                '}';
    }
}