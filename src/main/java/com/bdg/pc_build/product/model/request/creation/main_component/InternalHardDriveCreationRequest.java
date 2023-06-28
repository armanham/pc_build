package com.bdg.pc_build.product.model.request.creation.main_component;

import com.bdg.pc_build.product.model.request.creation.ProductCreationRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.Getter;

import static com.bdg.pc_build.util.Pattern.INTERNAL_HARD_DRIVE_INTERFACE_TYPE_ENUM_PATTERN;
import static com.bdg.pc_build.util.Pattern.WRONG_ENUM_PATTERN_COMMON_MESSAGE;

@Getter
public class InternalHardDriveCreationRequest extends ProductCreationRequest {

    @NotBlank(message = "internal_hard_drive_interface_type can not be blank")
    @Pattern(
            regexp = INTERNAL_HARD_DRIVE_INTERFACE_TYPE_ENUM_PATTERN,
            message = WRONG_ENUM_PATTERN_COMMON_MESSAGE
    )
    @JsonProperty(value = "internal_hard_drive_interface_type", required = true)
    private String internalHardDriveInterfaceType;

    @Positive(message = "capacity can not be negative or zero")
    @JsonProperty(value = "capacity", required = true)
    private Integer capacity;

    @Positive(message = "tdp can not be negative or zero")
    @JsonProperty(value = "tdp", required = true)
    private Integer tdp;

    public InternalHardDriveCreationRequest(
            final String name,
            final Double price,
            final Double purchasedPrice,
            final Integer count,
            final String internalHardDriveInterfaceType,
            final Integer capacity,
            final Integer tdp
    ) {
        super(name, price, purchasedPrice, count);
        this.internalHardDriveInterfaceType = internalHardDriveInterfaceType;
        this.capacity = capacity;
        this.tdp = tdp;
    }
}