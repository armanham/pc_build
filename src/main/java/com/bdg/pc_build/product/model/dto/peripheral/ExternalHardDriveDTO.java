package com.bdg.pc_build.product.model.dto.peripheral;

import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.entity.peripheral.ExternalHardDrive;
import com.bdg.pc_build.product.model.request.creation.peripheral.ExternalHardDriveCreationRequest;
import lombok.Getter;

@Getter
public class ExternalHardDriveDTO extends ProductDTO {

    private final Integer capacity;
    private final Integer tdp;

    public ExternalHardDriveDTO(final ExternalHardDrive entity) {
        super(entity.getId(), entity.getName(), entity.getPrice(), entity.getPurchasedPrice(), entity.getCount());
        this.capacity = entity.getCapacity();
        this.tdp = entity.getTdp();
    }

    public ExternalHardDriveDTO(final ExternalHardDriveCreationRequest request) {
        super(request.getName(), request.getPrice(), request.getPurchasedPrice(), request.getCount());

        if ((request.getCapacity() & request.getCapacity() - 1) != 0) {
            throw new IllegalArgumentException("Capacity of External Hard Drive must be a power of two: ");
        }
        this.capacity = request.getCapacity();
        this.tdp = request.getTdp();
    }

    @Override
    public String toString() {
        return "ExternalHardDriveDTO{" +
                "capacity=" + capacity +
                ", tdp=" + tdp +
                '}';
    }
}