package com.bdg.pc_build.product.model.request.creation.main_component;

import com.bdg.pc_build.product.model.request.creation.ProductCreationRequest;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

import static com.bdg.pc_build.checking.pattern.Pattern.*;
import static com.bdg.pc_build.checking.pattern.Pattern.WRONG_ENUM_PATTERN_COMMON_MESSAGE;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class InternalHardDriveCreationRequest extends ProductCreationRequest {

    @NotBlank(message = "'internal_hard_drive_interface_type' field can not be blank")
    @Pattern(
            regexp = INTERNAL_HARD_DRIVE_INTERFACE_TYPE_ENUM_PATTERN,
            message = WRONG_ENUM_PATTERN_COMMON_MESSAGE
    )
    @JsonProperty(value = "internal_hard_drive_interface_type", required = true)
    String internalHardDriveInterfaceType;

    @NotBlank(message = "'capacity' field can not be blank")
    @Pattern(
            regexp = POSITIVE_INTEGER_NUMBER_PATTERN,
            message = WRONG_POSITIVE_INTEGER_NUMBER_PATTERN_MESSAGE
    )
    @JsonProperty(value = "capacity", required = true)
    String capacity;

    @NotBlank(message = "'tdp' field can not be blank")
    @Pattern(
            regexp = POSITIVE_INTEGER_NUMBER_PATTERN,
            message = WRONG_POSITIVE_INTEGER_NUMBER_PATTERN_MESSAGE
    )
    @JsonProperty(value = "tdp", required = true)
    String tdp;

    public InternalHardDriveCreationRequest(
            final String name,
            final String price,
            final String purchasedPrice,
            final String count,
            final String internalHardDriveInterfaceType,
            final String capacity,
            final String tdp
    ) {
        super(name, price, purchasedPrice, count);
        this.internalHardDriveInterfaceType = internalHardDriveInterfaceType;
        this.capacity = capacity;
        this.tdp = tdp;
    }
}