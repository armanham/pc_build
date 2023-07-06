package com.bdg.pc_build.product.model.dto.main;

import com.bdg.pc_build.product.enumerations.InternalHardDriveInterfaceType;
import com.bdg.pc_build.product.model.dto.ProductDTO;
import com.bdg.pc_build.product.model.entity.main.InternalHardDrive;
import com.bdg.pc_build.product.model.request.creation.main.InternalHardDriveCreationRequest;
import lombok.Getter;

@Getter
public class InternalHardDriveDTO extends ProductDTO {

    private final InternalHardDriveInterfaceType internalHardDriveInterfaceType;
    private final Integer capacity;
    private final Integer tdp;

    public InternalHardDriveDTO(final InternalHardDrive entity) {
        super(entity.getId(), entity.getName(), entity.getPrice(), entity.getPurchasedPrice(), entity.getCount());
        this.internalHardDriveInterfaceType = entity.getInternalHardDriveInterfaceType();
        this.capacity = entity.getCapacity();
        this.tdp = entity.getTdp();
    }

    public InternalHardDriveDTO(final InternalHardDriveCreationRequest request) {
        super(request.getName(), request.getPrice(), request.getPurchasedPrice(), request.getCount());

        if ((request.getCapacity() & request.getCapacity() - 1) != 0) {
            throw new IllegalArgumentException("Capacity of Internal Hard Drive must be a power of two: ");
        }
        this.internalHardDriveInterfaceType = InternalHardDriveInterfaceType.valueOf(request.getInternalHardDriveInterfaceType().trim().toUpperCase());
        this.capacity = request.getCapacity();
        this.tdp = request.getTdp();
    }

    @Override
    public String toString() {
        return "InternalHardDriveDTO{" +
                "internalHardDriveInterfaceType=" + internalHardDriveInterfaceType +
                ", capacity=" + capacity +
                ", tdp=" + tdp +
                '}';
    }
}