package com.bdg.pc_build.product.model.dto.main_component;

import com.bdg.pc_build.product.enumerations.DDRType;
import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.entity.main_component.RAM;
import com.bdg.pc_build.product.model.request.creation.main_component.RAMCreationRequest;
import lombok.Getter;

@Getter
public class RAMDTO extends ProductDTO {

    private final DDRType ddrType;
    private final Integer countOfRam;
    private final Integer gbOfRam;
    private final Integer tdp;

    public RAMDTO(final RAM entity) {
        super(entity.getId(), entity.getName(), entity.getPrice(), entity.getPurchasedPrice(), entity.getCount());
        this.ddrType = entity.getDdrType();
        this.countOfRam = entity.getCountOfRam();
        this.gbOfRam = entity.getGbOfRam();
        this.tdp = entity.getTdp();
    }

    public RAMDTO(final RAMCreationRequest request) {
        super(request.getName(), request.getPrice(), request.getPurchasedPrice(), request.getCount());

        if ((request.getGbOfRam() & request.getGbOfRam() - 1) != 0) {
            throw new IllegalArgumentException("GB Of Ram must be a power of two: ");
        }
        this.ddrType = DDRType.valueOf(request.getDdrType().trim().toUpperCase());
        this.countOfRam = request.getCountOfRam();
        this.gbOfRam = request.getGbOfRam();
        this.tdp = request.getTdp();
    }

    @Override
    public String toString() {
        return "RAMDTO{" +
                "ddrType=" + ddrType +
                ", countOfRam=" + countOfRam +
                ", gbOfRam=" + gbOfRam +
                ", tdp=" + tdp +
                '}';
    }
}